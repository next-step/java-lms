package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @Test
    void create() {
        Session session = new Session(16, LocalDate.now(), LocalDate.now(),
                SessionType.FREE, SessionStatus.PREPARING, 50);

        assertThat(session).isNotNull();
    }
}
