package nextstep.courses.fixture;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeSessionRepository implements SessionRepository {

    private final Map<Long, Session> sessions;

    public FakeSessionRepository() {
        sessions = new HashMap<>();
    }

    @Override
    public void save(Session session) {
        sessions.put(session.getId(), session);
    }

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.of(sessions.get(id));
    }
}
