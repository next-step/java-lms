package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PayType;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTypeTest {

    @Test
    void isPaid() {
        SessionType sessionType = new SessionType(PayType.PAID, 1, 1);
        assertThatThrownBy(() -> sessionType.validatePaidSession(2, new Payment()))
            .isInstanceOf(SessionsException.class)
            .hasMessage("강의 최대 인원을 초과했습니다.");
    }

    @Test
    void isEnoughCapacity() {
        SessionType sessionType = new SessionType(PayType.PAID, 800000, 1);
        assertThatThrownBy(() -> sessionType.validatePaidSession(0, new Payment(1L, 2L, 3L, 799999L)))
            .isInstanceOf(SessionsException.class)
            .hasMessage("수강료와 결제한 금액이 다릅니다.");
    }

}
