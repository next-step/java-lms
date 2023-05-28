package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MockSessionRepository implements SessionRepository {

    public static final Session S1 = new Session(1L, "TDD with JAVA 16", 16, LocalDate.now(), LocalDate.now(),
            SessionType.FREE, SessionStatus.PREPARING, 50);
    public static final Session S2 = new Session(2L, "TDD with Kotlin 5", 5, LocalDate.now(), LocalDate.now(),
            SessionType.PAID, SessionStatus.RECRUITING, 50);
    private static final List<Session> sessions = Arrays.asList(S1, S2);

    @Override
    public Session findById(Long id) {
        return sessions.stream()
                .filter(s -> id == s.id())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
    }
}
