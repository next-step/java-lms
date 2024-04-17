package nextstep.courses.domain.vo;

import nextstep.courses.code.ChargeType;

public class Charge {

    private ChargeType type;

    private Long price;

    public Charge(Long price) {
        this(ChargeType.of(price), price);
    }

    public Charge(ChargeType type,
                  Long price) {
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
