package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;

public interface SessionType {
    boolean isFull(int count);

    int getCapacity();

    long getAmount();

    boolean equalMoney(Payment payment);
}
