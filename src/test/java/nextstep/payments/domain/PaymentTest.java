package nextstep.payments.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    @DisplayName("지불한 금액이 강의 금액과 일치하는지 확인한다")
    void payment_valid() {
        Payment payment = new Payment(UUID.randomUUID().toString(), 1L, 1L, 799_999L);
        assertThat(payment.isValid(800_000L)).isFalse();

        Payment payment2 = new Payment(UUID.randomUUID().toString(), 1L, 1L, 800_001L);
        assertThat(payment.isValid(800_000L)).isFalse();
    }
}