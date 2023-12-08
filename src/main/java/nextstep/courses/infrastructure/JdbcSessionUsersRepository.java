package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("sessionUsersRepository")
public class JdbcSessionUsersRepository implements SessionUsersRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionUsersRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SessionUsers findBy(long sessionId) {
        String sql = "select session_id, user_id, selection_status, status " +
                "from session_users " +
                "where session_id = ?";
        RowMapper<SessionUser> rowMapper = (rs, rowNum) -> new SessionUser(
                rs.getLong(1),
                rs.getLong(2),
                SelectionStatus.valueOf(rs.getString(3)),
                SessionUserStatus.valueOf(rs.getString(4))
        );
        List<SessionUser> sessionUsers = jdbcTemplate.query(sql, rowMapper, sessionId);
        Set<SessionUser> sessionUserSet = new HashSet<>(sessionUsers);
        return new SessionUsers(sessionUserSet);
    }

    @Override
    public void addUserFor(SessionUser sessionUser) {
        String sql = "insert into session_users(session_id, user_id, selection_status, status) values(?,?,?,?)";
        this.jdbcTemplate.update(sql, sessionUser.sessionId(), sessionUser.userId(), sessionUser.selectionStatus().toString(), sessionUser.sessionUserStatus().toString());
    }

    @Override
    public void save(SessionUser sessionUser) {
        String sql = "update session_users set status = ? where session_id = ? and user_id = ?";
        this.jdbcTemplate.update(sql, sessionUser.sessionUserStatus().toString(), sessionUser.sessionId(), sessionUser.userId());
    }
}
