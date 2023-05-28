package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockSessionRepository implements SessionRepository {

    private static final Session S1 = new Session(1L, "TDD with JAVA 16", 16,
            LocalDateTime.now(), LocalDateTime.now(),
            SessionType.FREE, SessionStatus.PREPARING, 50);
    private static final Session S2 = new Session(2L, "TDD with Kotlin 5", 5,
            LocalDateTime.now(), LocalDateTime.now(),
            SessionType.PAID, SessionStatus.RECRUITING, 50);
    private static final Map<Long, Session> sessions = new HashMap<>() {{
        sessions.put(1L, S1);
        sessions.put(2L, S2);
    }};

    @Override
    public Optional<Session> findById(Long id) {
        return Optional.ofNullable(sessions.get(id));
    }
}
