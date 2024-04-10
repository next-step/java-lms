package nextstep.courses.domain.session;

import nextstep.courses.domain.session.engine.ConcreteSessionEnrollment;
import nextstep.payments.domain.Payment;

public class CostSessionEnrollment extends ConcreteSessionEnrollment {


    protected CostSessionEnrollment(Long id, Long sessionId, SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        super(id, sessionId, status, capacity, fee);
    }

    @Override
    public void satisfy(Students students, Payment payment) {
        satisfyStatus();
        satisfyCapacity(students);
        satisfyFee(payment);
    }

    @Override
    public void satisfyStatus() {
        status.validateCanEnrollment();
    }

    @Override
    public void satisfyCapacity(Students students) {
        capacity.hasCapacity(students);
    }

    @Override
    public void satisfyFee(Payment payment) {
        fee.validatePaymentAmount(payment.getAmount());
    }
}
