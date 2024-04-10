package nextstep.sessions.domain;

import nextstep.payments.domain.Money;

public class PaidSession extends SessionType {
    private final int capacity;

    public PaidSession(int capacity, Money price) {
        super(price);
        this.capacity = capacity;
    }

    @Override
    boolean isFull(int userCount) {
        return capacity <= userCount;
    }
}
