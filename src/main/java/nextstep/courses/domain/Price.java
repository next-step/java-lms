package nextstep.courses.domain;

import java.util.Objects;

public class Price {

    private static final int MINIMUM_PRICE = 0;
    public static final String MESSAGE_INVALID_MINIMUM_PRICE = "가격은 %d원 이상이어야 합니다.";
    private final int price;

    public Price(int price) {
        inputValidation(price);
        this.price = price;
    }

    private void inputValidation(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_MINIMUM_PRICE, MINIMUM_PRICE));
        }
    }

    public int price() {
        return price;
    }

    public boolean samePrice(Price price){
        return this.equals(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return price == price1.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
