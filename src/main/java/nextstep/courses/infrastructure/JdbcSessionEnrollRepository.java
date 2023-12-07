package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionEnroll;
import nextstep.courses.domain.SessionEnrollRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionEnrollRepository")
public class JdbcSessionEnrollRepository implements SessionEnrollRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionEnrollRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionEnroll sessionEnroll) {
        String sql = "insert into session_enroll (session_id, student_id, payment_id, created_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionEnroll.getSessionId(), sessionEnroll.getStudentId(),
                sessionEnroll.getPaymentId(), sessionEnroll.getCreatedAt());
    }

    @Override
    public SessionEnroll findById(Long id) {
        String sql = "select id, session_id, student_id, payment_id, created_at from session_enroll where id = ?";
        RowMapper<SessionEnroll> rowMapper = (rs, rowNum) -> new SessionEnroll(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getString(4),
                toLocalDateTime(rs.getTimestamp(5)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
