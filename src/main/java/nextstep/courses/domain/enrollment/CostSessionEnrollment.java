package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.ConcreteSessionEnrollment;
import nextstep.payments.domain.Payment;

public class CostSessionEnrollment extends ConcreteSessionEnrollment {

    public CostSessionEnrollment(Long id, Long sessionId, SessionStatus status, int capacity, long fee) {
        super(id, sessionId, status, capacity, fee);
    }

    @Override
    public void satisfyFee(Payment payment) {
        fee.sameAs(payment);
    }
}
