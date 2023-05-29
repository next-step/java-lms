package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserCode;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.UserCodeException;
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
        String sql = "INSERT INTO ns_user " +
                "(user_code," +
                "password," +
                "name," +
                "email," +
                "created_at," +
                "updated_at) " +
                "values (?,?,?,?,?,?)";

        int update = jdbcTemplate.update(
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

    /*
    String sql = "INSERT INTO question (" +
                //"writer_id," +
                "title," +
                "contents," +
                "deleted," +
                "created_at," +
                "updated_at) " +
                "values (?,?,?,?,?)";
        int savedQuestionId = jdbcTemplate.update(
                sql,
                //question.getWriter().getUserId(),
                question.getTitle(),
                question.getContents(),
                question.getDeleted(),
                question.getCreatedDate(),
                question.getUpdatedDate()
        );
        return Question.of(
                new QuestionId((long) savedQuestionId),
                question
        );
     */

    @Deprecated(since = "이게 왜 실패하는지 알수 없음..")
    public NsUser saveV0(NsUser user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("ns_user").usingGeneratedKeyColumns("user_code");
        String userCode = Optional.ofNullable(user.getUserCode().value())
                .orElseThrow(UserCodeException::new);

        Map<String, Object> params = new HashMap<>() {
            {
                put("user_code", userCode);
                put("name", user.getName());
                put("email", user.getEmail());
                put("created_at", user.getCreatedAt());
                put("updated_at", user.getUpdatedAt());
            }
        };

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return new NsUser(
                new UserCode(key.toString()),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
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
    /*
    this.userCode = userCode;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
     */


}
