package nextstep.courses.domain;

public class SessionPrice {
    private static final int FREE_SESSION_PRICE = 0;

    private final int price;

    public SessionPrice(int price) {
        if(price < FREE_SESSION_PRICE) {
            throw new IllegalArgumentException("강의 최소 수강료는 " + FREE_SESSION_PRICE + "원입니다: " + price);
        }
        this.price = price;
    }

    public SessionChargeType chargeType() {
        return SessionChargeType.of(this);
    }

    public boolean isFree() {
        return price == FREE_SESSION_PRICE;
    }
}
