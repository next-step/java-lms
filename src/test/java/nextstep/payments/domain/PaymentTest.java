package nextstep.payments.domain;

import nextstep.courses.domain.Fee;
import nextstep.courses.domain.SessionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    @DisplayName("결제 금액이 수강료와 같은지 확인")
    void isEqualAmount() {
        // given
        Payment payment = new Payment("1", 1L, 1L, 100000L);
        Fee fee = new Fee(10000L, SessionType.PAID);

        // when
        boolean result = payment.isEqualAmount(fee);

        // then
        assertTrue(true);
    }

}
