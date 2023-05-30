package nextstep.courses.infrastructure;

import nextstep.courses.domain.user.Name;
import nextstep.courses.domain.user.User;
import nextstep.courses.domain.user.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

public class JdbcUserRepository implements UserRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(User user) {
        String sql = "insert into users(name) values(?)";
        return jdbcTemplate.update(sql, user.name().name());
    }

    @Override
    public User findById(Long id) {
        String sql = "select id, name from users where id = ?";
        RowMapper<User> rowMapper = (rs, rowNum) -> User.of(
            rs.getLong(1),
            Name.of(rs.getString(2))
        );
        return jdbcTemplate.query(sql, rowMapper, id).stream()
            .findAny()
            .orElse(User.of(0L, Name.of("")));
    }

    @Override
    public int update(User user) {
        String sql = "update users set name = ? where id = ?";
        return jdbcTemplate.update(sql, user.name().name(), user.id());
    }

    @Override
    public int delete(User user) {
        String sql = "delete from users where id = ?";
        return jdbcTemplate.update(sql, user.id());
    }
}
