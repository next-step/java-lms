package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MockSessionRepository implements SessionRepository {

    private static Map<String, Session> sessions = new HashMap<>() {{
        sessions.put("Java", new Session(16, LocalDate.now(), LocalDate.now(),
                SessionType.FREE, SessionStatus.RECRUITING, 50, 12));
        sessions.put("Kotlin", new Session(5, LocalDate.now(), LocalDate.now(),
                SessionType.FREE, SessionStatus.PREPARING, 50, 2));
    }};

    @Override
    public void save(Session session) {
        sessions.put(session.title(), session);
    }
}
