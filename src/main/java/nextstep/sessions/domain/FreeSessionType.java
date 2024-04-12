package nextstep.sessions.domain;

import nextstep.payments.domain.Money;

public class FreeSessionType extends SessionType {

    public FreeSessionType() {
        super(Money.ZERO);
    }

    @Override
    public boolean isFull(int userCount) {
        return false;
    }

}
