package nextstep.sessions.domain;

import nextstep.payments.domain.Money;

public class FreeSession extends SessionType {

    public FreeSession() {
        super(Money.ZERO);
    }

    @Override
    public boolean isFull(int userCount) {
        return false;
    }

}
