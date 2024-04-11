package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.engine.ConcreteSessionEnrollment;
import nextstep.payments.domain.Payment;

public class CostSessionEnrollment extends ConcreteSessionEnrollment {

    public CostSessionEnrollment(Long id, Long sessionId, SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        super(id, sessionId, status, capacity, fee);
    }

    @Override
    public void satisfy(Student student, Payment payment) {
        status.validateCanEnrollment();
        capacity.validateRemainingCapacity();
        fee.validatePaymentAmount(payment.getAmount());
    }

}
