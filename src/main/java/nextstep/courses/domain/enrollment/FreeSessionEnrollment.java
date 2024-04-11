package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.ConcreteSessionEnrollment;
import nextstep.payments.domain.Payment;

public class FreeSessionEnrollment extends ConcreteSessionEnrollment {

    public FreeSessionEnrollment(Long id, Long sessionId, SessionStatus status) {
        super(id, sessionId, status, SessionCapacity.INFINITY, SessionFee.FREE);
    }

    @Override
    public void satisfyFee(Payment payment) {
        payment.noPayment();
    }

}
