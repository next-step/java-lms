package nextstep.courses.domain.course;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionTest.FREE_SESSION1;
import static nextstep.courses.domain.SessionTest.PAID_SESSION1;

public class SessionsTest {

    public static final Sessions SESSIONS1 = new Sessions(FREE_SESSION1, PAID_SESSION1);

    @Test
    void create() {
        Assertions.assertThatNoException().isThrownBy(() -> {
            Sessions sessions = new Sessions(FREE_SESSION1, PAID_SESSION1);;
        });
    }

    @Test
    void add() {
        Sessions sessions = new Sessions();
        sessions.add(FREE_SESSION1);
        sessions.add(PAID_SESSION1);

        Assertions.assertThat(sessions).isEqualTo(SESSIONS1);
    }
}
