package nextstep.courses.domain.session;

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
