package nextstep.courses.domain.session;

public class Price {
    private int money;

    public Price(int money) {
        this.money = money;
    }

    public boolean isFree() {
        return this.money == 0;
    }

    public int money() {
        return money;
    }

    public static Price of(int money) {
        return new Price(money);
    }
}
