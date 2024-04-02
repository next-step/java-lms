package nextstep.sessions.domain;

import nextstep.money.Money;
import nextstep.payments.domain.Payment;

public interface SessionType {
    boolean isFull(int count);

    Money getAmount();

    boolean equalMoney(Payment payment);
}
