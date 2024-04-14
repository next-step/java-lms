package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private static String SELECT_BY_USER_ID_SQL = "SELECT id, user_id, password, name, email, created_at, updated_at FROM ns_user WHERE user_id = ?";
    private static String SELECT_BY_ID_SQL = "SELECT id, user_id, password, name, email, created_at, updated_at FROM ns_user WHERE id = ?";
    private JdbcOperations jdbcTemplate;

    private final RowMapper<NsUser> userRowMapper = (rs, rowNum) -> new NsUser(
            rs.getLong("id"),
            rs.getString("user_id"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email"),
            toLocalDateTime(rs.getTimestamp(6)),
            toLocalDateTime(rs.getTimestamp(7)));

    public JdbcUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<NsUser> findByUserId(String userId) {
        return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_USER_ID_SQL, userRowMapper, userId));
    }

    @Override
    public Optional<NsUser> findById(Long id) {

        return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, userRowMapper, id));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
