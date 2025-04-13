package nextstep.payments;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentTest {

    public static final Payment PAYMENT_FREE = new Payment(
            "12345", 1L, 100L, 0L
    );

    public static final Payment PAYMENT_1000 = new Payment(
            "12345", 1L, 100L, 1000L
    );

    @Test
    void 결제금액이_같으면_true를_반환한다() {
        assertTrue(PAYMENT_1000.isSameAmount(1000L));
    }

    @Test
    void 결제금액이_다르면_false를_반환한다() {
        assertFalse(PAYMENT_1000.isSameAmount(2000L));
    }
}
