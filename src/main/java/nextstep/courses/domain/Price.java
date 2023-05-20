package nextstep.courses.domain;

public class Price {
    private final int price;

    public Price(int price) {
        this.price = price;
    }

    public PriceType checkPriceType() {
        return PriceType.checkPriceType(this.price);
    }
}
