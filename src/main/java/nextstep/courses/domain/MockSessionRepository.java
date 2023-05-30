package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockSessionRepository implements SessionRepository {

    private static final Map<Long, Session> sessions = new HashMap<>() {{
        sessions.put(1L, new Session());
        sessions.put(2L, new Session());
    }};

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.ofNullable(sessions.get(id));
    }
}
