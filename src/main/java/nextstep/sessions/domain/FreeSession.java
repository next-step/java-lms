package nextstep.sessions.domain;

import nextstep.money.Money;
import nextstep.payments.domain.Payment;

public class FreeSession implements SessionType {

    private final int capacity;
    private final Money amount;

    public FreeSession() {
        this.capacity = 0;
        this.amount = Money.ZERO;
    }

    @Override
    public boolean isFull(int count) {
        return false;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    @Override
    public boolean equalMoney(Payment payment) {
        return amount.equals(payment.getAmount());
    }
}
