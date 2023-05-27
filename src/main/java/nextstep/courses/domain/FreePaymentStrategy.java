package nextstep.courses.domain;

public class FreePaymentStrategy implements PaymentStrategy {
    @Override
    public int priceValue() {
        return 0;
    }
}
