package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PayInfoTest {

    @Test
    void 유료_강의_결제_금액_불일치() {
        PayInfo payInfo = new PayInfo(PaidType.PAID, 800000L);
        Payment payment = new Payment(1L, 2L, 3L, 799999L);

        assertThatThrownBy(() -> payInfo.validatePayment(payment))
            .isInstanceOf(SessionsException.class)
            .hasMessage("수강료와 결제한 금액이 다릅니다.");
    }
    
}
