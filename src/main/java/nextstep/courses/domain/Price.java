package nextstep.courses.domain;

public class Price {

    private final boolean isFree;
    private final int price;

    public Price(boolean isFree, int price) {
        this.isFree = isFree;
        this.price = price;
    }
}
