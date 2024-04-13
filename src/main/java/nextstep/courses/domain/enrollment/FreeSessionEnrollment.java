package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.payments.domain.Payment;
import nextstep.payments.exception.PaymentAmountExistException;

public class FreeSessionEnrollment extends SessionEnrollment {

    public FreeSessionEnrollment(Long sessionId, SessionStatus status) {
        super(sessionId, status, SessionCapacity.INFINITY, SessionFee.FREE);
    }

    public FreeSessionEnrollment(SessionStatus status) {
        super(status, SessionCapacity.INFINITY, SessionFee.FREE);
    }

    @Override
    public void satisfyFee(Payment payment) {
        if (payment.paid()) {
            throw new PaymentAmountExistException(payment);
        }
    }

}
