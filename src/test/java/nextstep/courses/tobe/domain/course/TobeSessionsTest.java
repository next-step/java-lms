package nextstep.courses.tobe.domain.course;

import nextstep.courses.domain.course.Sessions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionTest.FREE_SESSION1;
import static nextstep.courses.domain.SessionTest.PAID_SESSION1;
import static nextstep.courses.tobe.domain.TobeFreeSessionTest.TFS1;
import static nextstep.courses.tobe.domain.TobePaidSessionTest.TPS1;

public class TobeSessionsTest {

    public static final TobeSessions SESSIONS1 = new TobeSessions(TFS1, TPS1);

    @Test
    void create() {
        Assertions.assertThatNoException().isThrownBy(() -> {
            TobeSessions sessions = new TobeSessions(TFS1, TFS1);
        });
    }

    @Test
    void add() {
        TobeSessions sessions = new TobeSessions();
        sessions.add(TFS1);
        sessions.add(TPS1);

        Assertions.assertThat(sessions).isEqualTo(SESSIONS1);
    }
}
