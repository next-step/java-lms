package nextstep.courses.domain.money;

import java.util.Objects;

public class Money {
    private int money;

    public Money(String money) {
        this(Integer.parseInt(money));
    }

    public Money(int money) {
        this.money = money;
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
