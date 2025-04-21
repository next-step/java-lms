package nextstep.payments.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentsTest {

    private Payments payments;

    @BeforeEach
    void setUp() {
        Payment payment1 = new Payment.Builder()
                .id("1")
                .sessionId(1L)
                .nsUserId(1L)
                .amount(1000L)
                .createdAt(LocalDateTime.now())
                .build();

        Payment payment2 = new Payment.Builder()
                .id("2")
                .sessionId(1L)
                .nsUserId(2L)
                .amount(1000L)
                .createdAt(LocalDateTime.now())
                .build();

        payments = new Payments(List.of(
                payment1,
                payment2
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
