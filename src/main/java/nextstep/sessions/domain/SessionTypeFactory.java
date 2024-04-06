package nextstep.sessions.domain;

import nextstep.money.Money;

public class SessionTypeFactory {

    private SessionTypeFactory() {
    }

    public static SessionType of(int capacity, long amount) {
        Money money = Money.wons(amount);
        if (!money.isZero()) {
            return new PaidSession(capacity, money);
        }

        return new FreeSession();
    }
}
