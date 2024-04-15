package nextstep.courses.domain.vo;

import nextstep.courses.code.ChargeType;

public class Charge {

    private ChargeType type;

    private Long price;

    public Charge() {
    }

    public Charge(ChargeType type,
                  Long price) {
        type.validateChargeType(price);

        this.type = type;
        this.price = price;
    }

    public ChargeType type() {
        return type;
    }

    public Long price() {
        return price;
    }

}