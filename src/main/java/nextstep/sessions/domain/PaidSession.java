package nextstep.sessions.domain;

import nextstep.money.Money;
import nextstep.payments.domain.Payment;

public class PaidSession implements SessionType {
    private static final int MIN_CAPACITY = 1;
    private final int capacity;
    private final Money amount;

    public PaidSession(int capacity, Money amount) {
        if (capacity < MIN_CAPACITY) {
            throw new IllegalArgumentException("강의 최소 인원은 " + MIN_CAPACITY + " 입니다");
        }

        if (amount.isZero()) {
            throw new IllegalArgumentException("강의 비용이 누락되었습니다");
        }

        this.capacity = capacity;
        this.amount = amount;
    }

    @Override
    public boolean isFull(int count) {
        return capacity <= count;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    @Override
    public boolean equalMoney(Payment payment) {
        return this.amount.equals(payment.getAmount());
    }
}
