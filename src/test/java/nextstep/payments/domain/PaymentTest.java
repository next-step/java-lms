package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @DisplayName("완벽히 지불되었는지 확인한다.")
    @Test
    void checkFullyPaid() {
        // given
        Payment payment = new Payment("NORMAL", 1L, 1L, 100_000L);

        // then
        assertThat(payment.isExactlyPaid(100_000L)).isTrue();
    }
}
