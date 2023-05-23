package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {

    @Test
    void create() {
        Session session = new Session(16, LocalDate.now(), LocalDate.now(),
                SessionType.FREE, SessionStatus.PREPARING, 50);

        assertThat(session).isNotNull();
    }

    @Test
    void 강의_상태가_모집중인지_확인() {
        Session session = new Session(16, LocalDate.now(), LocalDate.now(),
                SessionType.FREE, SessionStatus.RECRUITING, 50);

        assertTrue(session.isRecruiting());
    }
}
