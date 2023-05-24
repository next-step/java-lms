package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionRegisterTest {

    public static final Session S1 = new Session("TDD with JAVA 16", 16, LocalDate.now(), LocalDate.now(),
            SessionType.FREE, SessionStatus.PREPARING, 50, 12);
    public static final Session S2 = new Session("TDD with Kotlin 5", 5, LocalDate.now(), LocalDate.now(),
            SessionType.PAID, SessionStatus.RECRUITING, 50, 2);

    @Test
    void 강의상태가_모집중일_경우만_저장() {
        SessionRegister sessionRegister = new SessionRegister(new MockSessionRepository());

        sessionRegister.registerSession(S1);
        sessionRegister.registerSession(S2);

        assertThat(sessionRegister.count()).isEqualTo(1);
    }
}
