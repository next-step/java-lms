package nextstep.payments.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    public static final Payment PAY1 = new Payment();
    public static final Payment PAID_PAYMENT = new PaidPayment();
    public static final Payment NOTPAID_PAYMENT = new NotPaidPayment();

    @Test
    void isPaid() {
        assertThat(PAY1.isPaid()).isTrue();
    }
}
