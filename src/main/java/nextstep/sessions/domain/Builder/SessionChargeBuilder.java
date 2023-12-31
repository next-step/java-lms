package nextstep.sessions.domain.Builder;

import nextstep.sessions.domain.SessionCharge;

public class SessionChargeBuilder {
    private long price = 1000;
    private int limitCount = 1;

    public SessionChargeBuilder withPrice(long price) {
        this.price = price;
        return this;
    }

    public SessionChargeBuilder withLimitCount(int limitCount) {
        this.limitCount = limitCount;
        return this;
    }

    public SessionCharge build() {
        if (price > 0) {
            return new SessionCharge(true, price, limitCount);
        }
        return new SessionCharge(false, price, limitCount);
    }
}
