package nextstep.courses.domain;

public class Price {

    private final Free free;
    private final int price;

    public Price(Free free, int price) {
        this.free = free;
        this.price = price;
    }

    private void validPrice(int price) throws PriceException {
        if(price < 0) {
            throw new PriceException("가격은 0보다 작을 수 없습니다.");
        }
    }
}
