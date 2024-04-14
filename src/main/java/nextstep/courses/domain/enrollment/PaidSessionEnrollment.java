package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.exception.SessionFeeMismatchException;
import nextstep.payments.domain.Payment;

import java.util.List;

public class PaidSessionEnrollment extends SessionEnrollment {

    public PaidSessionEnrollment(Long sessionId, SessionEnrollment enrollment, List<SessionStudent> students) {
        super(sessionId, enrollment, students);
    }

    public PaidSessionEnrollment(Long sessionId, SessionStatus status, int capacity, long fee) {
        super(sessionId, status, capacity, fee);
    }

    public PaidSessionEnrollment(SessionStatus status, int capacity, long fee) {
        super(status, capacity, fee);
    }

    @Override
    public void satisfyFee(Payment payment) {
        if (fee.differentFrom(payment)) {
            throw new SessionFeeMismatchException(fee, payment);
        }
    }
}
