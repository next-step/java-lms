package nextstep.courses.domain;

public class Price {

    private static final int MINIMUM_PRICE = 0;
    public static final String 가격은_D_원_이상이어야_합니다 = "가격은 %d원 이상이어야 합니다.";
    private int price;

    public Price(int price) {
        inputValidation(price);
        this.price = price;
    }

    private void inputValidation(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(String.format(가격은_D_원_이상이어야_합니다, MINIMUM_PRICE));
        }
    }

    public int price() {
        return price;
    }

    public boolean samePrice(int price){
        return this.price == price;
    }
}
