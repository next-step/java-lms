package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Session> findById(long id) {
        String sql = "SELECT id, started_at, ended_at, session_name FROM session WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            long sessionId = rs.getLong("id");
            LocalDateTime startedAt = rs.getTimestamp("started_at").toLocalDateTime();
            LocalDateTime endedAt = rs.getTimestamp("ended_at").toLocalDateTime();
            String sessionName = rs.getString("session_name");
            return new Session(sessionId, startedAt, endedAt, sessionName);
        }, id));
    }

    @Override
    public int save(Session session) {
        String sql = "INSERT INTO session (id, started_at, ended_at, session_name) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getId(), session.getStartedAt(), session.getEndedAt(), session.getSessionName());
    }

}
