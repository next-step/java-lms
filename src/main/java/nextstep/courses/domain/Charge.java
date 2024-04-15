package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class Charge {
    private final ChargeStatus chargeStatus;
    private final long price;

    public Charge(ChargeStatus chargeStatus) {
        this.chargeStatus = chargeStatus;
        this.price = 0;
    }

    public Charge(ChargeStatus chargeStatus, long price) {
        chargeStatus.validate(price);
        this.chargeStatus = chargeStatus;
        this.price = price;
    }
    public boolean isCompletePay(Payment payment){
        return payment.isSame(price);
    }
}
