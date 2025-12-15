package nextstep.courses.domain;

import java.util.Objects;

public class Money {
    public static  final Money ZERO = new Money(0);

    private final int money;

    public Money(int money) {
        validation(money);
        this.money = money;
    }

    private void validation(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
    }

    public boolean matches(int money) {
        return this.money == money;
    }

    public boolean matches(Money money) {
        return matches(money.getMoney());
    }

    public int getMoney() {
        return this.money;
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

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                '}';
    }
}
