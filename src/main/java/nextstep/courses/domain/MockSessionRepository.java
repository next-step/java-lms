package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MockSessionRepository implements SessionRepository {

    public static final Session S1 = new Session("TDD with JAVA 16", 16, LocalDate.now(), LocalDate.now(),
            SessionType.FREE, SessionStatus.PREPARING, 50, 12);
    public static final Session S2 = new Session("TDD with Kotlin 5", 5, LocalDate.now(), LocalDate.now(),
            SessionType.PAID, SessionStatus.RECRUITING, 50, 2);

    private static Map<String, Session> sessions = new HashMap<>() {{
        sessions.put("Java", S1);
        sessions.put("Kotlin", S2);
    }};

    @Override
    public Session findByTitle(String title) {
        return sessions.get(title);
    }

    @Override
    public void save(Session session) {
        sessions.put(session.title(), session);
    }
}
