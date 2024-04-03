package nextstep.payments.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @Test
    void create() {
        LocalDateTime now = LocalDateTime.now();
        Payment payment = new Payment(1L, 1L, 1000L, now);
        assertThat(payment).isEqualTo(new Payment(1L, 1L, 1000L, now));
    }

}
