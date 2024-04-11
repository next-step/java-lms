package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @DisplayName("결제 금액이 동일한지 확인한다.")
    @Test
    void test01() {
        Payment payment = new Payment(2L, 3L, 10_000L);
        assertThat(payment.isSameAmount(10_000L)).isTrue();
    }
}