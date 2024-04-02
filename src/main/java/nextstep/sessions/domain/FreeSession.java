package nextstep.sessions.domain;

import nextstep.money.Money;
import nextstep.payments.domain.Payment;

public class FreeSession implements SessionType {

    @Override
    public boolean isFull(int count) {
        return false;
    }

    @Override
    public Money getAmount() {
        return Money.ZERO;
    }

    @Override
    public boolean equalMoney(Payment payment) {
        return Money.ZERO.equals(payment.getAmount());
    }
}
