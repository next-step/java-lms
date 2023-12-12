package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.sessionuser.SessionUser;
import nextstep.courses.domain.sessionuser.SessionUserRepository;
import nextstep.courses.domain.sessionuser.SessionUsers;
import nextstep.courses.domain.sessionuser.UserType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcSessionUserRepository implements SessionUserRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SessionUsers findBySession(Session session) {
        String sql = "select user_id, session_id, user_type, is_canceled from `session_user` where session_id = ?";
        RowMapper<SessionUser> rowMapper = (rs, rowNum) -> new SessionUser(
                rs.getLong(1),
                rs.getLong(2),
                UserType.valueOf(rs.getString(3)),
                rs.getBoolean(4)
        );

        List<SessionUser> queryResult = jdbcTemplate.query(sql, rowMapper, session.getId());
        return new SessionUsers(queryResult);
    }

    @Override
    public void save(SessionUser sessionUser) {
        String sql = "insert into `session_user` (user_id, session_id, user_type) values (?, ?, ?)";
        jdbcTemplate.update(sql, sessionUser.getUserId(), sessionUser.getSessionId(), sessionUser.userType());
    }
}
