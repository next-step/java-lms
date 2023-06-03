package nextstep.courses.domain.payment;

public class FreePaymentStrategy implements PaymentStrategy {
    @Override
    public int chargeValue() {
        return 0;
    }
}
