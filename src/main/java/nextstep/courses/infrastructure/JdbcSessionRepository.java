package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.ImageRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionTime;
import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("jdbcSessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;
    private final ImageRepository imageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, ImageRepository imageRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = imageRepository;
    }

    @Override
    public Session findById(Long id) {
        String sql = "SELECT * FROM session WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, sessionRowMapper(), id);
    }
    
    @Override
    public long save(Session session, Long courseId) {
        String sql = "INSERT INTO session (course_id, maximum_enrollment, period, image_id, opening_date_time, closing_date_time, session_status, session_type, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                courseId,
                session.getEnrollment().getMaximumEnrollment(),
                session.getPeriod(),
                session.getCoverImage().getId(),
                session.getSessionTime().getOpeningDateTime(),
                session.getSessionTime().getClosingDateTime(),
                session.getSessionStatus().getDescription(),
                session.getSessionType().getDescription(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }

    private LocalDateTime toLocalDateTime(Timestamp time) {
        if (time == null) {
            return null;
        }
        return time.toLocalDateTime();
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> new Session(
                rs.getLong("id"),
                rs.getString("period"),
                imageRepository.findById(rs.getLong("image_id")),
                new SessionTime(toLocalDateTime(rs.getTimestamp("opening_date_time")), toLocalDateTime(rs.getTimestamp("closing_date_time"))),
                SessionType.valueOf(rs.getString("session_type")),
                SessionStatus.valueOf(rs.getString("session_status")),
                new Enrollment(rs.getInt("maximum_enrollment"))
        );
    }
}
