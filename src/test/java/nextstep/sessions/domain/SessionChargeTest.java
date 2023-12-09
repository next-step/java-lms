package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionChargeTest {
    public static final SessionCharge FREE = new SessionCharge(false, 0, 0);
    public static final SessionCharge CHARGE_1000 = new SessionCharge(true, 1000, 10);
    public static final SessionCharge CHARGE_100 = new SessionCharge(true, 100, 1);

    @DisplayName("무료 강의는 수강료와 수강 인원에 제한이 없이 객체를 생성한다.")
    @Test
    void chargeFreeTest() {
        assertThat(new SessionCharge(false, 10000, 0)).isInstanceOf(SessionCharge.class);
        assertThat(new SessionCharge(false, 0, 1)).isInstanceOf(SessionCharge.class);
    }

    @DisplayName("유료 강의는 수강료와 수강 인원을 전달해야 객체를 생성한다.")
    @Test
    void chargeTest() {
        assertThat(new SessionCharge(true, 10000, 10)).isInstanceOf(SessionCharge.class);
    }

    @DisplayName("유료 강의의 수강료가 0원이거나, 수강 인원이 0인 경우 IllegalArgumentException을 던진다.")
    @Test
    void chargeExceptionTest() {
        assertThatThrownBy(() -> new SessionCharge(true, 0, 10))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SessionCharge(true, 1000, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
