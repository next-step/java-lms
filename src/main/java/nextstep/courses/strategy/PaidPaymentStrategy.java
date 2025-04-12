package nextstep.courses.strategy;

import nextstep.payments.domain.Payment;

public class PaidPaymentStrategy implements PaymentStrategy {
    private final long price;

    public PaidPaymentStrategy(long price) {
        this.price = price;
    }

    @Override
    public boolean payable(Payment payment) {
        if (payment.isSameAmount(price))
            return true;
        return false;
    }
}
