package nextstep.courses.domain;

public class Price {

    private boolean isFree;
    private int money;

    public Price(boolean isFree, int money) {
        this.isFree = isFree;
        this.money = money;
    }

    public boolean isFree() {
        return isFree;
    }
}
