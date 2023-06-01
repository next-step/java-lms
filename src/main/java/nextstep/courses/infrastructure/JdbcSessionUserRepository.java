package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionUser;
import nextstep.courses.domain.SessionUserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository("sessionUserRepository")
public class JdbcSessionUserRepository implements SessionUserRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveAll(List<SessionUser> sessionUsers) {
        String sql = "insert into session_registered_users (session_id, user_id, created_at) values(?, ?, ?)";
        return Arrays.stream(jdbcTemplate.batchUpdate(sql, sessionUsers, sessionUsers.size(), (ps, argument) -> {
            ps.setLong(1, argument.getSessionId());
            ps.setString(2, argument.getUserId());
            ps.setTimestamp(3, Timestamp.valueOf(argument.getCreatedAt()));
        })).map(array -> array.length).reduce(0, Integer::sum);
    }

    @Override
    public List<SessionUser> findBySession(Long id) {
        String sql = "select id, session_id, user_id, created_at from session_registered_users where session_id = ?";
        return jdbcTemplate.query(sql, getSessionUserRowMapper(), id);
    }

    private RowMapper<SessionUser> getSessionUserRowMapper() {
        return (rs, rowNum) -> new SessionUser(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                toLocalDateTime(rs.getTimestamp(4)));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
