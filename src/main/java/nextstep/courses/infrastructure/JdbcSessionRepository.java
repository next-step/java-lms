package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionId;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
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

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session save(Session session) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("session").usingGeneratedKeyColumns("session_id");

        Map<String, Object> sqlParameters = new HashMap<>() {{
//            put("session_id", Optional.ofNullable(session.getSessionId().value()).orElseGet(null));
            //put("image_id", Optional.ofNullable(session.getImage()).orElseGet(null));
            put("term", session.getTerm());
            put("price", session.getPrice());
            put("session_status", session.getSessionStatus().name());
            put("max_student_count", session.getMaxStudentCount().longValue());
            put("start_date", session.getStartDate());
            put("end_date", session.getEndDate());
        }};
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(sqlParameters));
        session.setSessionId(new SessionId(key.longValue()));
        return session;
    }

    @Override
    public Optional<Session> findBySessionId(SessionId sessionId) {
        String sql = "select * from session where session_id =" + sessionId.value().toString();
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper()));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Session> findAll() {
        return jdbcTemplate.query("SELECT * from session", rowMapper());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from session");
    }

    private RowMapper<Session> rowMapper() {
        return (rs, rowNum) -> {
            return new Session(
                    new SessionId(rs.getLong("session_id")),
                    null,
                    rs.getLong("term"),
                    rs.getLong("price"),
                    SessionStatus.valueOf(rs.getString("session_status")),
                    rs.getLong("max_student_count"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date")
            );
        };
    }
}
