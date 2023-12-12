package nextstep.payments.domain;

public class PaymentTest {

    public static final Payment PAID_1 = new Payment(1L, 1L, 800_000L);
    public static final Payment PAID_2 = new Payment(1L, 1L, 1_200_000L);
    public static final Payment FREE = new Payment(2L, 2L, 0L);

}