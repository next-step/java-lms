package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.SessionListener;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sessionListenerRepository")
public class JdbcSessionListenerRepository implements SessionListenerRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionListenerRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionListener sessionListener) {
        String sql = "INSERT INTO session_listener (session_id, ns_user_id) VALUES(?, ?)";
        return jdbcTemplate.update(sql, sessionListener.getSessionId(), sessionListener.getNsUserId());
    }

    @Override
    public void saveAll(List<SessionListener> sessionListeners) {
        String sql = "INSERT INTO session_listener (session_id, ns_user_id) VALUES(?, ?)";
        jdbcTemplate.batchUpdate(sql, sessionListeners, sessionListeners.size(), (ps, sessionListener) -> {
            ps.setLong(1, sessionListener.getSessionId());
            ps.setLong(2, sessionListener.getNsUserId());
        });
    }

    @Override
    public List<SessionListener> findAllBySessionId(long sessionId) {
        String sql = "SELECT session_id, ns_user_id FROM session_listener WHERE session_id = ?";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new SessionListener(rs.getLong(1), rs.getLong(2)),
                sessionId);
    }

    @Override
    public int delete(SessionListener sessionListener) {
        String sql = "DELETE FROM session_listener WHERE session_id = ? AND ns_user_id = ?";
        return jdbcTemplate.update(sql, ps -> {
            ps.setLong(1, sessionListener.getSessionId());
            ps.setLong(2, sessionListener.getNsUserId());
        });
    }
}
