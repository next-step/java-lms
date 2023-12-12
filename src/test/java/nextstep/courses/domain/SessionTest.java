package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE;
import static nextstep.courses.domain.SessionPeriodTest.NORMAL_SESSION_PERIOD;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    public static final Session FREE_SESSION = makeSession(new SessionPaymentCondition());
    public static final Session PAID_SESSION = makeSession(new SessionPaymentCondition(800_000L, 120L));

    private static Session makeSession(SessionPaymentCondition sessionPaymentCondition) {
        return new Session(1L, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentCondition);
    }

    @Test
    @DisplayName("생성_null 이거나 0_throw IllegalArgumentException")
    void 생성_validate() {
        SessionPaymentCondition sessionPaymentCondition = new SessionPaymentCondition(0L, 0L);
        assertThatThrownBy(() -> new Session(0L, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentCondition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        assertThatThrownBy(() -> new Session(null, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.RECRUITING, sessionPaymentCondition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
    }

    @Test
    @DisplayName("checkStatus_SessionStatus.RECRUITING 외_throw CannotEnrollException")
    void checkStatus() {
        Session preparingSession = new Session(1L, NORMAL_COVER_IMAGE, NORMAL_SESSION_PERIOD, SessionStatus.PREPARING, new SessionPaymentCondition());

        assertThatThrownBy(preparingSession::checkStatus)
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("강의가 모집중인 상태가 아닙니다.");
    }
}
