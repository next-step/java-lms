package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class EnrollmentManager {

    private final SessionFee fee;
    private final EnrollmentCount count;
    private final SessionStatus status;

    public EnrollmentManager(Long fee, Integer count) {
        this(new SessionFee(fee), new EnrollmentCount(count), SessionStatus.RECRUITING);
    }

    public EnrollmentManager(Long fee, Integer count, SessionStatus status) {
        this(new SessionFee(fee), new EnrollmentCount(count), status);
    }

    private EnrollmentManager(SessionFee fee, EnrollmentCount count, SessionStatus status) {
        this.fee = fee;
        this.count = count;
        this.status = status;
    }

    public boolean canEnroll(Payment payment) {
        if(!status.isRecruiting()){
            return false;
        }
        if(fee.isFree()){
            return true;
        }
        if(!count.hasRemainingCount()){
            return false;
        }
        return fee.canPurchase(payment);
    }
}
