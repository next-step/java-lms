package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionId;
import nextstep.courses.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
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
        throw new RuntimeException("Not Yet Implemented");
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from session");
    }
}
