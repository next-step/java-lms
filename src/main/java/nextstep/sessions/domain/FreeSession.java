package nextstep.sessions.domain;

import nextstep.money.Money;

public class FreeSession implements SessionType {

    @Override
    public boolean isFull(int count) {
        return false;
    }

    @Override
    public Money getAmount() {
        return Money.ZERO;
    }
}
