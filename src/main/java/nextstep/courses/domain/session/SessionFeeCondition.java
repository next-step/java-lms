package nextstep.courses.domain.session;

import nextstep.courses.exception.MismatchSessionFeeException;
import nextstep.courses.exception.SessionException;
import nextstep.payments.domain.Payment;

public class SessionFeeCondition implements SessionEnrollmentCondition {

    private final Payment payment;

    public SessionFeeCondition(Payment payment) {
        this.payment = payment;
    }

    @Override
    public void satisfy(SessionEnrollment enrollment) throws SessionException {
        if (!enrollment.matchFee(payment.getAmount())) {
            throw new MismatchSessionFeeException(enrollment.getFee().get(), payment.getAmount());
        }
    }

}
