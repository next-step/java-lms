package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class EnrollmentManager {

    private final SessionFee fee;
    private final EnrollmentCount count;

    public EnrollmentManager(Long fee, Integer count) {
        this(new SessionFee(fee), new EnrollmentCount(count));
    }

    public EnrollmentManager(SessionFee fee, EnrollmentCount count) {
        this.fee = fee;
        this.count = count;
    }

    public boolean canEnroll(Payment payment) {
        if(fee.isFree()){
            return true;
        }
        if(!count.hasRemainingCount()){
            return false;
        }
        return fee.canPurchase(payment);
    }
}
