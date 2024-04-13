package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.ConcreteSessionEnrollment;
import nextstep.courses.exception.SessionFeeMismatchException;
import nextstep.payments.domain.Payment;

public class PaidSessionEnrollment extends ConcreteSessionEnrollment {

    public PaidSessionEnrollment(Long id, Long sessionId, SessionStatus status, int capacity, long fee) {
        super(id, sessionId, status, capacity, fee);
    }

    @Override
    public void satisfyFee(Payment payment) {
        if (fee.differentFrom(payment)) {
            throw new SessionFeeMismatchException(fee, payment);
        }
    }
}
