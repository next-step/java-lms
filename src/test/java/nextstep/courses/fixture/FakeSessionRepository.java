package nextstep.courses.fixture;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeSessionRepository implements SessionRepository {

    private final AtomicLong idGenerator;
    private final Map<Long, Session> sessions;

    public FakeSessionRepository() {
        idGenerator = new AtomicLong();
        sessions = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Session session) {
        if (session.getId() == null) {
            long newId = idGenerator.getAndIncrement();
            session.assignId(newId);
        }

        sessions.put(session.getId(), session);
    }

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.of(sessions.get(id));
    }
}
