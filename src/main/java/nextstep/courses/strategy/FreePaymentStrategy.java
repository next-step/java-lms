package nextstep.courses.strategy;

import nextstep.payments.domain.Payment;

public class FreePaymentStrategy implements PaymentStrategy {
    @Override
    public boolean payable(Payment payment) {
        return true;
    }
}
