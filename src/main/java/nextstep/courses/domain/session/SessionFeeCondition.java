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
    public void satisfy(Session session) throws SessionException {
        if (!session.matchFee(payment.getAmount())) {
            throw new MismatchSessionFeeException(session.getFee(), payment.getAmount());
        }
    }

}
