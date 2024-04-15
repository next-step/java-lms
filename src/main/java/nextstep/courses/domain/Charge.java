package nextstep.courses.domain;

public class Charge {
    private final ChargeStatus chargeStatus;
    private final int price;

    public Charge(ChargeStatus chargeStatus) {
        this.chargeStatus = chargeStatus;
        this.price = 0;
    }

    public Charge(ChargeStatus chargeStatus, int price){
        chargeStatus.validate(price);
        this.chargeStatus = chargeStatus;
        this.price = price;
    }
}
