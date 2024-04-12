package nextstep.sessions.domain;

import nextstep.payments.domain.Money;

public class PaidSessionType extends SessionType {
    private final int capacity;

    public PaidSessionType(int capacity, Money price) {
        super(price);
        this.capacity = capacity;
    }

    @Override
    boolean isFull(int userCount) {
        return capacity <= userCount;
    }
}
