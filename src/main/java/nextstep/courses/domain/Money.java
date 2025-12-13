package nextstep.courses.domain;

public class Money {
    private int money;
    public Money(int money) {
        validation(money);
        this.money = money;
    }

    private void validation(int money) {
        if( money < 0) {
            throw new IllegalArgumentException("금액은 0 이상이어야 합니다.");
        }
    }

    public boolean matches(int money) {
        return this.money == money;
    }
}
