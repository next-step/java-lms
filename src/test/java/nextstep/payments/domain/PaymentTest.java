package nextstep.payments.domain;

import nextstep.users.domain.NsUserTest;

public class PaymentTest {
    public static final Payment FREE_PAYMENT = Payment.ofFree(1L, NsUserTest.JAVAJIGI);
    public static final Payment PAID_PAYMENT_10000 = Payment.ofPaid(1L, 1L, NsUserTest.JAVAJIGI, 10_000L);
    public static final Payment PAID_PAYMENT_8000 = Payment.ofPaid(2L, 1L, NsUserTest.SANJIGI, 8_000L);
}
