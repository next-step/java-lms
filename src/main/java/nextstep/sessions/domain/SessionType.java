package nextstep.sessions.domain;

import nextstep.money.Money;

public interface SessionType {
    boolean isFull(int count);

    Money getAmount();
}
