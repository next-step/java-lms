package nextstep.sessions.domain;

import nextstep.money.Money;
import nextstep.payments.domain.Payment;

public class FreeSession implements SessionType {

    private final int capacity;
    private final Money money;

    public FreeSession() {
        this(0, Money.ZERO);
    }

    public FreeSession(int capacity, Money money) {
        this.capacity = capacity;
        this.money = money;
    }

    @Override
    public boolean isFull(int count) {
        return false;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public long getAmount() {
        return money.getAmount();
    }

    @Override
    public boolean equalMoney(Payment payment) {
        return money.equals(payment.getAmount());
    }
}
