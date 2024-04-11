package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.ConcreteSessionEnrollment;
import nextstep.payments.domain.Payment;
import nextstep.payments.exception.PaymentAmountExistException;

public class FreeSessionEnrollment extends ConcreteSessionEnrollment {

    public FreeSessionEnrollment(Long id, Long sessionId, SessionStatus status) {
        super(id, sessionId, status, SessionCapacity.INFINITY, SessionFee.FREE);
    }

    @Override
    public void satisfyFee(Payment payment) {
        if (payment.paid()) {
            throw new PaymentAmountExistException(payment);
        }
    }

}
