package nextstep.courses.domain;

public class ChargeInfo {
    private final ChargeType chargeType;
    private final long price;

    public ChargeInfo(ChargeType chargeType, long price) {
        this.chargeType = chargeType;
        this.price = price;
    }

    public boolean isPaymentValid(int payment) {
        return price == payment;
    }
}
