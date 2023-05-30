package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserCode;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.UserCodeException;
import nextstep.utils.PrimaryKeyCodeMakerRandom;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
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
        return saveV1(user);
    }

    private NsUser saveV1(NsUser user) {
        String sql = "INSERT INTO ns_user " +
                "(user_code," +
                "password," +
                "name," +
                "email," +
                "created_at," +
                "updated_at) " +
                "values (?,?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                user.getUserCode().value(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return new NsUser(
                user.getUserCode(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Deprecated(since = "NULL not allowed for column \"USER_CODE\" 라는 메시지가 계속 뜨는데 이게 아무래도 PK id 가 auto generated 될 때 사용하는 방식이라서 그런듯 하다. 나중에 수정하겠습니다..")
    public NsUser saveV0(NsUser user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("ns_user").usingGeneratedKeyColumns("user_code");
        UserCode userCode = user.getUserCode() == null ? UserCode.any(PrimaryKeyCodeMakerRandom.of()) : user.getUserCode();
        Map<String, Object> params = new HashMap<>() {
            {
                put("user_code", userCode.value());
                put("password", user.getPassword());
                put("name", user.getName());
                put("email", user.getEmail());
                put("created_at", user.getCreatedAt());
                put("updated_at", user.getUpdatedAt());
            }
        };
        jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return this.findByUserCode(userCode).orElseThrow(()-> new RuntimeException("저장에 실패하였습니다"));
    }

    @Override
    public List<NsUser> findAll() {
        return jdbcTemplate.query("SELECT * FROM ns_user", rowMapper());
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
}
