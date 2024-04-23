package nextstep.courses.domain;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 0원 이상이여야 합니다.");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public boolean isEqualOrSmaller(Money money) {
        return this.value <= money.value;
    }
}
