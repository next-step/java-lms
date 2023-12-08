package nextstep.courses.domain;

public class Price {

    private static final int ZERO_PRICE = 0;

    private int price;

    public Price(int price) {
        validate(price);
        this.price = price;
    }

    private void validate(int price) {
        if(price < ZERO_PRICE) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다");
        }
    }
}
