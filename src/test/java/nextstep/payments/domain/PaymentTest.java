package nextstep.payments.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PaymentTest {
    public static final Payment PAYMENT_1000 = new Payment("p1", 1L, 1L, 1000L);

    @Test
    void isPaidFor_returnsTrue_whenAmountMatchesFee() {
        assertThat(PAYMENT_1000.isPaidFor(1000)).isTrue();
    }

    @Test
    void isPaidFor_returnsFalse_whenAmountDoesNotMatchFee() {
        assertThat(PAYMENT_1000.isPaidFor(1500)).isFalse();
    }

    @Test
    void isPaidFor_returnsFalse_whenFeeIsZero() {
        assertThat(PAYMENT_1000.isPaidFor(0)).isFalse();
    }

    @Test
    void isPaidFor_returnsTrue_forLargeAmounts() {
        Payment largePayment = new Payment("p1", 1L, 1L, 10_000_000L);
        assertThat(largePayment.isPaidFor(10_000_000)).isTrue();
    }

}
