package nextstep.qna.domain;

import nextstep.payments.domain.Payment;

public class EnrollmentManager {

    private final Long fee;
    private final Integer studentNumber;

    public EnrollmentManager(Long fee, Integer studentNumber) {
        this.fee = fee;
        this.studentNumber = studentNumber;
    }

    public boolean canEnroll(Payment payment) {
        if(isFreeSession()){
            return true;
        }
        if(hasNoMoreSeats()){
            return false;
        }
        return payment.canPay(this.fee);
    }

    private boolean hasNoMoreSeats() {
        return this.studentNumber <= 0;
    }

    private boolean isFreeSession() {
        return this.fee == 0L;
    }
}
