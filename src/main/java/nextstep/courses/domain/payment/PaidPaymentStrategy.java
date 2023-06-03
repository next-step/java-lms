package nextstep.courses.domain.payment;

public class PaidPaymentStrategy implements PaymentStrategy {

    private final int price;

    public PaidPaymentStrategy(int price) {
        this.price = price;
    }

    @Override
    public int chargeValue() {
        return price;
    }
}
