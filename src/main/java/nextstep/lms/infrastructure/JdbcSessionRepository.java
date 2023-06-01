package nextstep.lms.infrastructure;

import nextstep.lms.domain.*;
import nextstep.lms.repository.CourseRepository;
import nextstep.lms.repository.LmsUserRepository;
import nextstep.lms.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;
    private final LmsUserRepository lmsUserRepository;
    private final CourseRepository courseRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, LmsUserRepository lmsUserRepository, CourseRepository courseRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.lmsUserRepository = lmsUserRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (title, course_id, creator_id, price, status, max_applicant_count, cover_img, start_date, end_date, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getTitle(), session.getCourseId(), session.getCreatorId(), session.getPriceValue(), session.getStatus(), session.getMaxApplicantCount(), session.getCoverImgURL(), session.getStartDate(), session.getEndDate(),session.getCreatedAt());
    }

    @Override
    public Session findById(Long sessionId) {
        String sql = "SELECT * FROM session WHERE id = ?";
        Session session = jdbcTemplate.queryForObject(sql, new Object[]{sessionId}, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            Long courseId = rs.getLong("course_id");
            Long creatorId = rs.getLong("creator_id");
            Integer price = rs.getInt("price");
            String status = rs.getString("status");
            Integer maxApplicantCount = rs.getInt("max_applicant_count");
            String coverImg = rs.getString("cover_img");
            LocalDate startDate = rs.getDate("start_date").toLocalDate();
            LocalDate endDate = rs.getDate("end_date").toLocalDate();
            LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp("created_at"));
            LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp("updated_at"));

            Course course = courseRepository.findById(courseId);
            LmsUser creator = lmsUserRepository.findById(creatorId);

            return new Session(id, title, course, creator, new SessionPrice(price), SessionStatus.valueOf(status), maxApplicantCount, new ArrayList<>(), new SessionCoverImg(coverImg), startDate, endDate, createdAt, updatedAt);
        });
        return session;
    }

    @Override
    public Session findByTitle(String sessionTitle) {
        String sql = "SELECT * FROM session WHERE title = ?";
        Session session = jdbcTemplate.queryForObject(sql, new Object[]{sessionTitle}, (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            Long courseId = rs.getLong("course_id");
            Long creatorId = rs.getLong("creator_id");
            Integer price = rs.getInt("price");
            String status = rs.getString("status");
            Integer maxApplicantCount = rs.getInt("max_applicant_count");
            String coverImg = rs.getString("cover_img");
            LocalDate startDate = rs.getDate("start_date").toLocalDate();
            LocalDate endDate = rs.getDate("end_date").toLocalDate();
            LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp("created_at"));
            LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp("updated_at"));

            Course course = courseRepository.findById(courseId);
            LmsUser creator = lmsUserRepository.findById(creatorId);

            return new Session(id, title, course, creator, new SessionPrice(price), SessionStatus.valueOf(status), maxApplicantCount, new ArrayList<>(), new SessionCoverImg(coverImg), startDate, endDate, createdAt, updatedAt);
        });
        return session;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
