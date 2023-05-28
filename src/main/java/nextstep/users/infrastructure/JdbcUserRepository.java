package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserCode;
import nextstep.users.domain.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<NsUser> findByUserCode(UserCode userCode) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            "select " +
                                    "user_code, password, name, email, created_at, updated_at " +
                                    "from ns_user " +
                                    "where user_code = ?",
                            rowMapper(),
                            userCode.value()
                    )
            );
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    @Override
    public NsUser save(NsUser user) {

    }

    @Override
    public List<NsUser> findAll() {
        throw new RuntimeException();
    }

    private RowMapper<NsUser> rowMapper() {
        return (rs, rowNum) -> new NsUser(
                new UserCode(rs.getString("user_code")),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
    }
    /*
    this.userCode = userCode;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
     */


}
