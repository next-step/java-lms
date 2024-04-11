package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.engine.ConcreteSessionEnrollment;
import nextstep.payments.domain.Payment;

public class FreeSessionEnrollment extends ConcreteSessionEnrollment {

    public FreeSessionEnrollment(Long id, Long sessionId, SessionStatus status) {
        super(id, sessionId, status, new SessionCapacity(id, sessionId, SessionCapacity.INFINITY), new SessionFee(id, sessionId, SessionFee.FREE));
    }

    @Override
    public void satisfy(Student student, Payment payment) {
        status.validateCanEnrollment();
    }

}
