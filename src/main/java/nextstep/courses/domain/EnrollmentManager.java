package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class EnrollmentManager {

    private final SessionFee fee;
    private final Integer studentNumber;

    public EnrollmentManager(Long fee, Integer studentNumber) {
        this(new SessionFee(fee), studentNumber);
    }

    public EnrollmentManager(SessionFee fee, Integer studentNumber) {
        this.fee = fee;
        this.studentNumber = studentNumber;
    }

    public boolean canEnroll(Payment payment) {
        if(fee.isFree()){
            return true;
        }
        if(hasNoMoreSeats()){
            return false;
        }
        return fee.canPurchase(payment);
    }

    private boolean hasNoMoreSeats() {
        return this.studentNumber <= 0;
    }
}
