package nextstep.courses.domain;

import nextstep.courses.domain.vo.Price;
import nextstep.payments.domain.Payment;

public class PaidStrategy implements ChargeStrategy {

    private Payment payment;

    public PaidStrategy(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean isPaid(Price price) {
        return price.paid(payment.amount());
    }
}
