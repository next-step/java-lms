package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionNextStepUserRepository;
import nextstep.users.domain.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository("jdbcSessionNextStepUserRepository")
public class JdbcSessionNextStepUserRepository implements SessionNextStepUserRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionNextStepUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(long sessionId, long userId) {
        String sql = "INSERT INTO session_next_step_user (session_id, user_id, created_at, updated_at) VALUES(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, userId, LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public List<User> findUsersBySessionId(long sessionId) {
        String sql = "SELECT u.* FROM next_step_user u INNER JOIN session_next_step_user su ON u.id = su.user_id WHERE su.session_id = ?";

        RowMapper<User> rowMapper = (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

}
