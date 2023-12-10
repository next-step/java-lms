package nextstep.courses.domain;

public class Charge {
    private static final int FREE_SESSION_PRICE = 0;

    private final ChargeStatus status;

    private final int price;

    public Charge(ChargeStatus status) {
        this(status, FREE_SESSION_PRICE);
    }

    public Charge(ChargeStatus status, int price) {
        status.valid(price);
        this.status = status;
        this.price = price;
    }
}
