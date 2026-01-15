package nextstep.courses.domain.money;

import java.util.Objects;

public class Money {
    public static final Money FREE = new Money(0);
    private final Long money;

    public Money(String money) {
        this(Long.parseLong(money));
    }

    public Money(long money) {
        this.money = money;
    }

    public boolean isEqualTo(Money other) {
        return money == other.money;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(money);
    }
}
