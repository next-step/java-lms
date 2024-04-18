package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.student.SessionStudent;
import nextstep.payments.domain.Payment;
import nextstep.payments.exception.PaymentAmountExistException;

import java.util.List;

public class FreeSessionEnrollment extends SessionEnrollment {

    public FreeSessionEnrollment(SessionEnrollment enrollment, List<SessionStudent> students) {
        super(enrollment, students);
    }

    public FreeSessionEnrollment(RecruitmentStatus recruitmentStatus) {
        super(recruitmentStatus, SessionCapacity.INFINITY, SessionFee.FREE);
    }

    @Override
    public void satisfyFee(Payment payment) {
        if (payment.paid()) {
            throw new PaymentAmountExistException(payment);
        }
    }

}
