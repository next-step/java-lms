package nextstep.courses.domain.charge;

import nextstep.courses.enums.ChargeStatus;

public class Charge {

    private final ChargeStatus status;

    private final int price;

    public Charge(ChargeStatus status) {
        this(status, 0);
    }

    public Charge(ChargeStatus status, int price) {
        status.valid(price);
        this.status = status;
        this.price = price;
    }

}
