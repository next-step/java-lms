package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionId;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session save(Session session) {
        throw new RuntimeException("Not Yet Implemented");
    }

    @Override
    public Optional<Session> findById(SessionId sessionId) {
        throw new RuntimeException("Not Yet Implemented");
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
