package nextstep.sessions.domain;

import java.util.Objects;

public class Price {

    private final long price;

    public Price(long price) {
        this.price = price;
    }

    public boolean isNotSamePrice(long amount) {
        return this.price != amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Price price1 = (Price) object;
        return price == price1.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

}
