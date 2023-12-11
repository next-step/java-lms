package nextstep.courses.domain;

import nextstep.courses.exception.PaymentException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {

    @Test
    @DisplayName("결제 금액과 수강료가 일치하지 않으면 예외를 반환한다.")
    void 결제_금액과_수강료_불일치_예외() {
        Payment payment = new Payment("ID", 0L, 0L, 10000L);
        assertThatThrownBy(() -> payment.isAbleToPayment(20000L))
                .isInstanceOf(PaymentException.class);
    }
}
