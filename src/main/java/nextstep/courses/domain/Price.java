package nextstep.courses.domain;

import java.util.Objects;

public class Price {
    private static final String PRICE_NOT_NEGATIVE = "가격은 0 이상이어야 합니다.";
    private final Long value;

    private Price(Long value) {
        validate(value);
        this.value = value;
    }

    private void validate(Long value) {
        if (value < 0) {
            throw new IllegalArgumentException(PRICE_NOT_NEGATIVE);
        }
    }

    public boolean match(Long amount) {
        return Objects.equals(this.value, amount);
    }

    public Long getValue() {
        return value;
    }

    public static Price of(Long amount) {
        return new Price(amount);
    }

    public static Price free() {
        return new Price(0L);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(value, price.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
