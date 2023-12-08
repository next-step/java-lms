package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.sessionuser.SessionUser;
import nextstep.courses.domain.sessionuser.SessionUsers;
import nextstep.courses.domain.sessionuser.SessionUsersRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class JdbcSessionUsersRepository implements SessionUsersRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionUsersRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SessionUsers findBySession(Session session) {
        String sql = "select user_id, session_id from session where session_id = ?";
        RowMapper<SessionUser> rowMapper = (rs, rowNum) -> new SessionUser(
                new NsUser(rs.getLong(1)),
                new Session(rs.getLong(2))
        );
        List<SessionUser> result = jdbcTemplate.query(sql, rowMapper, session.getId());
        return new SessionUsers(result);
    }

    @Override
    public void save(SessionUser sessionUser) {
        String sql = "insert into session_users (user_id, session_id) values (?, ?)";
        jdbcTemplate.update(sql, sessionUser.getNsUser().getUserId(), sessionUser.getSession().getId());
    }
}
