package nextstep.courses.infrastructure.session;

import nextstep.courses.domain.session.SessionUser;
import nextstep.courses.domain.session.SessionUserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sessionUserRepository")
public class JdbcSessionUserRepository implements SessionUserRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionUser user) {
        String sql = "insert into sessions_user (session_id, user_id) values(?, ?)";
        return jdbcTemplate.update(sql, user.getSessionId(), user.getUserId());
    }

    @Override
    public List<SessionUser> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, user_id from sessions_user where session_id = ?";
        RowMapper<SessionUser> rowMapper = (rs, rowNum) -> new SessionUser(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("user_id"));
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
