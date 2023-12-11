package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Students;
import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.payments.domain.Payment;

public class PaidEnroll implements Enroll {

    private PaidEnroll() {
    }

    public static PaidEnroll from() {
        return new PaidEnroll();
    }

    @Override
    public void validate(EnrollmentInfo enrollmentInfo, Students students, Payment payment) {
        validateFullStudents(enrollmentInfo.enrollmentMax(), students);
        payment.complete(enrollmentInfo.amount());
    }

    private void validateFullStudents(long enrollmentMax, Students students) {
        if (students.isFull(enrollmentMax)) {
            throw new EnrollmentMaxExceededException("최대 수강 인원을 초과하였습니다.");
        }
    }
}
