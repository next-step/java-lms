package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MockSessionRepository implements SessionRepository {

    public static final Session S1 = new Session(1L, "TDD with JAVA 16", 16,
            LocalDateTime.now(), LocalDateTime.now(),
            SessionType.FREE, SessionStatus.PREPARING, 50);
    public static final Session S2 = new Session(2L, "TDD with Kotlin 5", 5,
            LocalDateTime.now(), LocalDateTime.now(),
            SessionType.PAID, SessionStatus.RECRUITING, 50);
    private static final List<Session> sessions = Arrays.asList(S1, S2);

    @Override
    public Optional<Session> findById(Long id) {
        return sessions.stream()
                .filter(s -> id == s.id())
                .findFirst();
    }
}
