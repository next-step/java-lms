package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PaymentTest {

    @Test
    @DisplayName("price와 매개변수가 다른 경우 true 반환")
    void isDifferentAmount() {
        long correctAmount = 1000L;
        Payment payment = new Payment("test", 0L, 0L, correctAmount);
        assertThat(payment.isDifferentAmount(correctAmount)).isFalse();
        assertThat(payment.isDifferentAmount(2000L)).isTrue();
    }

}