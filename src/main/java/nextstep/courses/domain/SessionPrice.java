package nextstep.courses.domain;

import java.util.Objects;

public class SessionPrice {
    private static final long FREE_SESSION_PRICE = 0L;

    private final long price;
    private final SessionChargeType type;

    public SessionPrice(long price) {
        this(price, SessionChargeType.of(price));
    }

    public SessionPrice(long price, SessionChargeType type) {
        if(price < FREE_SESSION_PRICE) {
            throw new IllegalArgumentException("강의 최소 수강료는 " + FREE_SESSION_PRICE + "원입니다: " + price);
        }
        this.price = price;
        this.type = type;
    }

    public SessionChargeType type() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionPrice)) return false;
        SessionPrice that = (SessionPrice) o;
        return price == that.price && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, type);
    }
}
