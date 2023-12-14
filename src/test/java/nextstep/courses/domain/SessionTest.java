package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionPeriodTest.NORMAL_SESSION_PERIOD;
import static nextstep.courses.domain.SessionStatusTest.RECRUITING_SESSION_STATUS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    public static final Session FREE_SESSION = makeSession(new SessionCondition());
    public static final Session PAID_SESSION = makeSession(new SessionCondition(800_000L, 120L));

    private static Session makeSession(SessionCondition sessionPaymentCondition) {
        return new Session(1L, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS, sessionPaymentCondition);
    }

    @Test
    @DisplayName("생성_null 이거나 0_throw IllegalArgumentException")
    void 생성_validate() {
        SessionCondition sessionPaymentCondition = new SessionCondition(0L, 0L);
        assertThatThrownBy(() -> new Session(0L, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS,sessionPaymentCondition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        assertThatThrownBy(() -> new Session(null, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS, sessionPaymentCondition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
    }
}
