package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {
    @Test
    @DisplayName("결제 금액이 주어진 가격과 일치하는지 확인한다")
    void isAmountEqual() {
        // given
        Payment payment = new Payment("payment1", 1L, 1L, 10000L);

        // when & then
        assertThat(payment.isAmountEqual(10000)).isTrue();
        assertThat(payment.isAmountEqual(5000)).isFalse();
    }
} 