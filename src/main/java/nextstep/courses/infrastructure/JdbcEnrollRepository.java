package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enroll;
import nextstep.courses.domain.EnrollId;
import nextstep.courses.domain.EnrollRepository;
import nextstep.courses.domain.EnrollStatus;
import nextstep.courses.domain.SessionId;
import nextstep.users.domain.UserCode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("EnrollRepository")
public class JdbcEnrollRepository implements EnrollRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcEnrollRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Enroll save(Enroll enroll) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("enroll").usingGeneratedKeyColumns("enroll_id");

        Map<String, Object> sqlParameters = new HashMap<>() {{
            put("enroll_id", enroll.getEnrollId() == null ? null : enroll.getEnrollId().value());
            put("session_id", enroll.getSessionId().value());
            put("user_code", enroll.getUserCode().value());
            put("enroll_status", enroll.getEnrollStatus());
        }};
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(sqlParameters));
        return new Enroll(
                new EnrollId(key.longValue()),
                enroll.getSessionId(),
                enroll.getUserCode(),
                enroll.getEnrollStatus()
        );
    }

    @Override
    public Optional<Enroll> findById(EnrollId enrollId) {
        String sql = "select * from enroll where enroll_id =" + enrollId.value().toString();
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper()));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Enroll> findAll() {
        return jdbcTemplate.query("select * from enroll", rowMapper());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from enroll");
    }

    private RowMapper<Enroll> rowMapper() {
        return (rs, rowNum) -> {
            return new Enroll(
                    new EnrollId(rs.getLong("enroll_id")),
                    new SessionId(rs.getLong("session_id")),
                    new UserCode(rs.getString("user_code")),
                    EnrollStatus.valueOf(rs.getString("enroll_status")
                    )
            );
        };
    }
}
