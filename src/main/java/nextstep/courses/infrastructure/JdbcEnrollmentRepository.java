package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.SelectionStatus;
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
        String sql = "INSERT INTO enrollment (session_id, user_id, status, enrolled_at) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, enrollment.getSessionId(), enrollment.getUserId(), enrollment.getStatus(), enrollment.getEnrollmentDate());
    }

    @Override
    public List<Enrollment> findBySessionId(Long sessionId) {
        String sql = "SELECT session_id, user_id, status, enrolled_at FROM enrollment WHERE session_id = ?";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Enrollment(
                        rs.getLong("session_id"),
                        rs.getLong("user_id"),
                        SelectionStatus.valueOf(rs.getString("status")),
                        toLocalDateTime(rs.getTimestamp("enrolled_at"))
                ), sessionId
        );
    }

    @Override
    public void updateStatus(Enrollment enrollment) {
        String sql = "update enrollment set status = ? where session_id = ? and user_id = ?";
        jdbcTemplate.update(sql, enrollment.getStatus(), enrollment.getSessionId(), enrollment.getUserId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
