package nextstep.courses.domain;

public class FreePaymentStrategy implements PaymentStrategy {
    @Override
    public int getPrice() {
        return 0;
    }
}
