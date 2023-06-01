package nextstep.courses.domain;

public class FreePaymentStrategy implements PaymentStrategy {
    @Override
    public int chargeValue() {
        return 0;
    }
}
