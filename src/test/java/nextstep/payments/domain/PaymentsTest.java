package nextstep.payments.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsTest {

    private Payments payments;

    @BeforeEach
    void setUp() {
        payments = new Payments(List.of(
                new Payment("1", 1L, 1L, 1000L),
                new Payment("2", 1L, 2L, 1000L)
        ));
    }

    @Test
    void get() {
        Payment payment = payments.get(1L);
        assertThat(payment.getNsUserId()).isEqualTo(1L);
    }

    @Test
    void paidCorrectly() {
        boolean result = payments.paidCorrectly(1L, 1000L);
        assertThat(result).isTrue();
    }

    @Test
    void paymentNotFound() {
        boolean result = payments.paidCorrectly(3L, 1000L);
        assertThat(result).isFalse();
    }

    @Test
    void amountMismatch() {
        boolean result = payments.paidCorrectly(1L, 2000L);
        assertThat(result).isFalse();
    }
}
