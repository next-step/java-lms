package nextstep.courses.domain;

public class Price {

    private final int price;

    public Price(int price) {
        this.price = price;
    }

    public PriceType getType() {

        if(this.price == 0){
            return PriceType.FREE;
        }

        return PriceType.PAID;
    }
}
