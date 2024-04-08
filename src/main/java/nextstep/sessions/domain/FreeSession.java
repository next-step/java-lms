package nextstep.sessions.domain;

import nextstep.capacity.Capacity;
import nextstep.money.Money;
import nextstep.payments.domain.Payment;

public class FreeSession implements SessionType {

    private final Capacity capacity;
    private final Money fee;


    public FreeSession() {
        this(new Capacity(), Money.ZERO);
    }

    public FreeSession(int capacity, long amount) {
        this(new Capacity(capacity), Money.wons(amount));
    }

    public FreeSession(Capacity capacity, Money fee) {
        this.fee = fee;
        this.capacity = capacity;
    }

    @Override
    public boolean isFull(int count) {
        return false;
    }

    @Override
    public int getCapacity() {
        return this.capacity.getCapacity();
    }

    @Override
    public long getAmount() {
        return fee.getAmount();
    }

    @Override
    public boolean equalMoney(Payment payment) {
        return fee.equals(payment.getAmount());
    }
}
