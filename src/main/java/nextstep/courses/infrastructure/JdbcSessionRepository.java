package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    private final SessionUserRepository sessionUserRepository;
    private final CourseRepository courseRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionUserRepository = new JdbcSessionUserRepository(jdbcTemplate);
        this.courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Override
    public int save(Session session) {
        String sql = "INSERT INTO session (id, title, creator_id, course_id, max_user_count, start_date, end_date, " +
                "cover_image_url, price, pay_type, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getId(), session.getTitle(), session.getCreatorId(), session.getCourseId(),
                session.getMaxUserCount(), session.getStartDate(), session.getEndDate(), session.getCoverImageUrl(),
                session.getPrice(), session.getPayType().toString(), session.getStatus().toString(), session.getCreatedAt(),
                session.getUpdatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "SELECT id, title, creator_id, course_id, max_user_count, start_date, end_date, cover_image_url, " +
                "price, status, created_at, updated_at FROM session WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                courseRepository.findById(rs.getLong(4)),
                rs.getDate(6).toLocalDate(),
                rs.getDate(7).toLocalDate(),
                rs.getString(8),
                rs.getLong(9),
                SessionStatus.of(rs.getString(10)),
                rs.getInt(5),
                sessionUserRepository.findBySession(rs.getLong(1)),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12))
        ), id);
    }

    @Override
    public List<Session> findByCourse(Course course) {
        String sql = "SELECT id, title, creator_id, course_id, max_user_count, start_date, end_date, cover_image_url, " +
                "price, status, created_at, updated_at FROM session WHERE course_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                course,
                rs.getDate(6).toLocalDate(),
                rs.getDate(7).toLocalDate(),
                rs.getString(8),
                rs.getLong(9),
                SessionStatus.of(rs.getString(10)),
                rs.getInt(5),
                sessionUserRepository.findBySession(rs.getLong(1)),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12))
        ), course.getId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
