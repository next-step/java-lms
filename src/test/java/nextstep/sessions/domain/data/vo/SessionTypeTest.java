package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.session.PaidType;
import nextstep.sessions.domain.data.session.SessionType;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTypeTest {

    @Test
    void 유료_강의_최대_인원_초과() {
        SessionType sessionType = new SessionType(PaidType.PAID, 800000, 2);

        assertThatThrownBy(() -> sessionType.validateSession(2, new Payment()))
            .isInstanceOf(SessionsException.class)
            .hasMessage("강의 최대 인원을 초과했습니다.");
    }

    @Test
    void 무료_강의_인원_금액_제한_조건_무시() {
        SessionType sessionType = new SessionType(PaidType.FREE, 0, 9999);
        Payment payment = new Payment(1L, 2L, 3L, 1000000L);

        Assertions.assertDoesNotThrow(() -> sessionType.validateSession(10000, payment));
    }

}
