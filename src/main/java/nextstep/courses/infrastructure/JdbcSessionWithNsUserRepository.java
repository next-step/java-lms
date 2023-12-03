package nextstep.courses.infrastructure;

import nextstep.courses.domain.repository.SessionWithNsUserRepository;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class JdbcSessionWithNsUserRepository implements SessionWithNsUserRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionWithNsUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean save(Long sessionId, Long nsUserId) {
        String sql = "insert into session_ns_user (session_id, ns_user_id) values(? , ?)";
        return jdbcTemplate.update(sql, sessionId, nsUserId) > 0;
    }

    @Override
    public List<Integer> findBySessionId(Long sessionId) {
        String sql = "select ns_user_id from session_ns_user where session_id = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, sessionId);
    }

    @Override
    public int findByNsUserId(Long nsUserId) {
        String sql = "select session_id from session_ns_user where ns_user_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, nsUserId);
    }
}
