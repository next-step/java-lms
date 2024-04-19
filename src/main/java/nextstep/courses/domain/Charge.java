package nextstep.courses.domain;

public class Charge {
    private final ChargeStatus chargeStatus;

    private final int price;

    public Charge(ChargeStatus chargeStatus) {
        this(chargeStatus, 0);
    }

    public Charge(ChargeStatus chargeStatus, int price) {
        chargeStatus.valid(price);
        this.chargeStatus = chargeStatus;
        this.price = price;
    }

    public ChargeStatus getChargeStatus() {
        return chargeStatus;
    }

    public int getPrice() {
        return price;
    }
}
