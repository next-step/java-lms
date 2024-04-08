package nextstep.sessions.domain;

import nextstep.capacity.Capacity;
import nextstep.money.Money;
import nextstep.payments.domain.Payment;

public class PaidSession implements SessionType {
    private static final int MIN_CAPACITY = 1;

    private final Capacity capacity;
    private final Money fee;


    public PaidSession(int capacity, long amount) {
        this(new Capacity(capacity), Money.wons(amount));
    }

    public PaidSession(int capacity, Money fee) {
        this(new Capacity(capacity), fee);
    }

    public PaidSession(Capacity capacity, Money fee) {
        if (capacity.isLessThan(MIN_CAPACITY)) {
            throw new IllegalArgumentException("강의 최소 인원은 " + MIN_CAPACITY + " 입니다");
        }

        if (fee.isZero()) {
            throw new IllegalArgumentException("강의 최소 인원은 " + MIN_CAPACITY + " 입니다");
        }

        this.capacity = capacity;
        this.fee = fee;
    }

    @Override
    public boolean isFull(int count) {
        return this.capacity.isLessThanOrEqual(count);
    }

    @Override
    public int getCapacity() {
        return this.capacity.getCapacity();
    }

    @Override
    public long getAmount() {
        return this.fee.getAmount();
    }

    @Override
    public boolean equalMoney(Payment payment) {
        return this.fee.equals(payment.getAmount());
    }
}
