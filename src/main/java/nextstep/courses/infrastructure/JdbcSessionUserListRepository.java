package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionUserListRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionUserListRepository")
public class JdbcSessionUserListRepository implements SessionUserListRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionUserListRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final Long userId, final Long sessionId) {
        String sql = "insert into session_user_list (user_id, session_id) values (?, ?)";
        return jdbcTemplate.update(sql, userId, sessionId);
    }

    @Override
    public List<NsUser> findAllBySessionId(final Long sessionId) {
        String sql = "select u.id, u.user_id, u.password, u.name, u.email, u.created_at, u.updated_at " +
                "from session_user_list su " +
                "inner join ns_user u " +
                "on su.user_id = u.id " +
                "where su.session_id = ?";

        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
