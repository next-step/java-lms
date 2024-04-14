package nextstep.courses.domain.session.strategy;

import java.util.Objects;

public class Money {

    static final int MINIMUM_MONEY = 0;

    private final int value;

    public Money(final int value) {
        validateMoneyIsNotNegative(value);

        this.value = value;
    }

    private void validateMoneyIsNotNegative(final int value) {
        if (value < MINIMUM_MONEY) {
            throw new IllegalArgumentException("금액은 0원 미만일 수 없습니다. 금액: " + value);
        }
    }

    public boolean isLessThan(final Money otherMoney) {
        return this.value < otherMoney.value;
    }

    @Override
    public boolean equals(final Object otherMoney) {
        if (this == otherMoney) {
            return true;
        }

        if (otherMoney == null || getClass() != otherMoney.getClass()) {
            return false;
        }

        return this.value == ((Money)otherMoney).value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
