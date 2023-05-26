package nextstep.courses.domain;

public class SessionPrice {
    private static final int FREE_SESSION_PRICE = 0;

    private final int price;
    private final SessionChargeType type;

    public SessionPrice(int price) {
        if(price < FREE_SESSION_PRICE) {
            throw new IllegalArgumentException("강의 최소 수강료는 " + FREE_SESSION_PRICE + "원입니다: " + price);
        }
        this.price = price;
        this.type = SessionChargeType.of(price);
    }

    public SessionChargeType type() {
        return type;
    }
}
