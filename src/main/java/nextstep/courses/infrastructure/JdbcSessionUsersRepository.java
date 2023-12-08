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
        String sql = "select ns.id, ns.user_id, ns.password, ns.name, ns.email, ns.created_at, ns.updated_at " +
                "from session_users us " +
                "join ns_user ns on ns.id = us.user_id " +
                "where us.session_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7))
        );
        List<NsUser> users = jdbcTemplate.query(sql, rowMapper, sessionId);
        Set<NsUser> userSet = new HashSet<>(users);
        return new SessionUsers(userSet);
    }

    @Override
    public void addUserFor(long sessionId, long userId) {
        String sql = "insert into session_users(session_id, user_id, status) values(?,?,?)";
        this.jdbcTemplate.update(sql, sessionId, userId, SessionUserStatus.WAITING.toString());
    }

    @Override
    public void updateSessionUserStatus(long sessionId, long userId, SessionUserStatus sessionUserStatus) {
        String sql = "update session_users set status = ? where session_id = ? and user_id = ?";
        this.jdbcTemplate.update(sql, sessionUserStatus.toString(), sessionId, userId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
