package nextstep.payments.domain;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal value;

    public Money(Long value) {
        this(new BigDecimal(value));
    }

    public Money(BigDecimal value) {
        Assert.notNull(value, "금액은 필수 데이터입니다.");

        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
