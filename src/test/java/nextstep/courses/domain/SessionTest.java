package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {

    public static final Session S1 = new Session(1L, "TDD with JAVA 16", 16, LocalDate.now(), LocalDate.now(),
            SessionType.FREE, SessionStatus.PREPARING, 50, 12);
    public static final Session S2 = new Session(2L, "TDD with Kotlin 5", 5, LocalDate.now(), LocalDate.now(),
            SessionType.PAID, SessionStatus.RECRUITING, 50, 2);

    @Test
    void create() {
        assertThat(S1).isNotNull();
    }

    @Test
    void 강의_상태가_모집중인지_확인() {
        assertTrue(S2.isRecruiting());
    }
}
