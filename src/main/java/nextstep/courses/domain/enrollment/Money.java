package nextstep.courses.domain.enrollment;

import java.util.Objects;

public class Money {
    private final long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    public boolean isEqualTo(Money other) {
        return this.equals(other);
    }

    public long value() {
        return amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Money money = (Money) object;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
