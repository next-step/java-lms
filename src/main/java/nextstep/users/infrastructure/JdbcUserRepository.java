package nextstep.users.infrastructure;

import nextstep.users.domain.User;
import nextstep.users.domain.UserRepository;
import nextstep.users.domain.enums.UserStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String sql = "select id, user_id, password, name, email, created_at, updated_at from next_step_user where user_id = ?";
        RowMapper<User> rowMapper = (rs, rowNum) -> new User(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, userId));
    }

    @Override
    public Optional<User> findById(long id) {
        String sql = "select * from next_step_user where id = ?";
        RowMapper<User> rowMapper = (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")),
             UserStatus.of(rs.getString("user_status")));

        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
                .map(Timestamp::toLocalDateTime)
                .orElse(null);
    }
}
