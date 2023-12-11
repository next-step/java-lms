package nextstep.courses.domain;

import java.util.Objects;

public class Charge {
    private static final int FREE_SESSION_PRICE = 0;

    private final ChargeStatus status;

    private final int price;

    public Charge(ChargeStatus status) {
        this(status, FREE_SESSION_PRICE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Charge charge = (Charge) o;
        return price == charge.price && status == charge.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, price);
    }

    public Charge(ChargeStatus status, int price) {
        status.valid(price);
        this.status = status;
        this.price = price;
    }
}
