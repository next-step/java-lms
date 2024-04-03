package nextstep.courses.domain.vo;

import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(0);
    private long amount;

    public Money(long amount) {
        this.amount = amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
