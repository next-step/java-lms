package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.status.SessionStatus;
import nextstep.payments.domain.Payment;
import nextstep.payments.exception.PaymentAmountExistException;

import java.util.List;

public class FreeSessionEnrollment extends SessionEnrollment {

    public FreeSessionEnrollment(SessionEnrollment enrollment, List<SessionStudent> students) {
        super(enrollment, students);
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
