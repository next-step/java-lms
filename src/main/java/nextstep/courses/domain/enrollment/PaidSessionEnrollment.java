package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.exception.SessionFeeMismatchException;
import nextstep.payments.domain.Payment;

import java.util.List;

public class PaidSessionEnrollment extends SessionEnrollment {

    public PaidSessionEnrollment(SessionEnrollment enrollment, List<SessionStudent> students) {
        super(enrollment, students);
    }

    public PaidSessionEnrollment(RecruitmentStatus recruitmentStatus, int capacity, long fee) {
        super(recruitmentStatus, capacity, fee);
    }

    @Override
    public void satisfyFee(Payment payment) {
        if (fee.differentFrom(payment)) {
            throw new SessionFeeMismatchException(fee, payment);
        }
    }
}
