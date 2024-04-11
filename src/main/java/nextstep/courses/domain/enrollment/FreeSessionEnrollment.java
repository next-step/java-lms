package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.engine.ConcreteSessionEnrollment;
import nextstep.payments.domain.Payment;

public class FreeSessionEnrollment extends ConcreteSessionEnrollment {

    public FreeSessionEnrollment(Long id, Long sessionId, SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        super(id, sessionId, status, capacity, fee);
    }

    @Override
    public void satisfy(Students students, Payment payment) {
        status.validateCanEnrollment();
    }

}
