package nextstep.courses.domain.value;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(long value) {
        this((int) value);
    }

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < 0) {
            throw new RuntimeException("금액은 0 이상이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int value() {
        return this.value;
    }
}
