package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.enrollment.EnrollmentStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("sessionEnrollmentRepository")
public class JdbcSessionEnrollmentRepository implements SessionEnrollmentRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Long sessionId, Long userId) {
        save(sessionId, userId, EnrollmentStatus.PENDING_APPROVAL);
    }

    @Override
    public void save(Long sessionId, Long userId, EnrollmentStatus status) {
        String sql = "INSERT INTO session_enrollment (session_id, user_id, status, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                sessionId,
                userId,
                status.name(),
                LocalDateTime.now()
        );
    }

    @Override
    public void updateStatus(Long sessionId, Long userId, EnrollmentStatus status) {
        String sql = "UPDATE session_enrollment SET status = ?, updated_at = ? WHERE session_id = ? AND user_id = ?";
        jdbcTemplate.update(sql,
                status.name(),
                LocalDateTime.now(),
                sessionId,
                userId
        );
    }

    @Override
    public List<Long> findUserIdsBySessionId(Long sessionId) {
        String sql = "SELECT user_id FROM session_enrollment WHERE session_id = ?";
        return jdbcTemplate.queryForList(sql, Long.class, sessionId);
    }

    @Override
    public Map<Long, EnrollmentStatus> findUserStatusesBySessionId(Long sessionId) {
        String sql = "SELECT user_id, status FROM session_enrollment WHERE session_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, sessionId);

        Map<Long, EnrollmentStatus> result = new HashMap<>();
        for (Map<String, Object> row : rows) {
            Long userId = (Long) row.get("user_id");
            String statusStr = (String) row.get("status");
            EnrollmentStatus status = EnrollmentStatus.valueOf(statusStr);
            result.put(userId, status);
        }

        return result;
    }
}
