package nextstep.payments;

import nextstep.payments.domain.Payment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    void name() {
        Assertions.assertThat(new Payment("결제", 1L, 1L, 3000L).isPossibleToTakeSession(3000L)).isTrue();
    }
}
