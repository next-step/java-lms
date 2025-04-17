package nextstep.courses.infrastructure;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionEnrollmentRepository")
public class JdbcSessionEnrollmentRepository implements SessionEnrollmentRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Long sessionId, Long userId) {
        String sql = "INSERT INTO session_enrollment (session_id, user_id, status, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                sessionId,
                userId,
                "Y",
                LocalDateTime.now()
        );
    }

    @Override
    public List<Long> findUserIdsBySessionId(Long sessionId) {
        String sql = "SELECT user_id FROM session_enrollment WHERE session_id = ?";
        return jdbcTemplate.queryForList(sql, Long.class, sessionId);
    }
} 