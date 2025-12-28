package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.repository.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (session_id, user_id, enrolled_at) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, enrollment.getSessionId(), enrollment.getUserId(), enrollment.getEnrollmentDate());
    }

    @Override
    public List<Enrollment> findBySessionId(Long sessionId) {
        String sql = "SELECT session_id, user_id, enrolled_at FROM enrollment WHERE session_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Enrollment(
                        rs.getLong("session_id"),
                        rs.getLong("user_id"),
                        toLocalDateTime(rs.getTimestamp("enrolled_at"))
                ), sessionId
        );
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
