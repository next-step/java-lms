package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class JdbcSessionRepository implements SessionRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Session> findById(long id) {
        return Optional.empty();
    }

    @Override
    public int save(Session session) {
        String sql = "INSERT INTO session (id, started_at, ended_at, session_name, image_id, session_register_details_id) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getId(), session.getStartedAt(), session.getEndedAt(), session.getSessionName(), session.getImageId(), session.getSessionRegisterDetailsId());
    }
}
