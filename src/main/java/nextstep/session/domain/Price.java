package nextstep.session.domain;

import nextstep.payments.domain.Payment;

public class Price {

    public final long price;

    public Price(long price) {
        this.price = price;
    }

    public boolean isFullyPaid(Payment payment) {
        return payment.isExactlyPaid(price);
    }
}
