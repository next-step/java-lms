package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidEnrollment implements Enrollment {

    private PaidEnrollment() {

    }

    public static PaidEnrollment from() {
        return new PaidEnrollment();
    }

    @Override
    public void enroll(Session session, NsUser student, Payment payment) {
        validate(session, payment);
        session.addStudent(student);
    }

    private void validate(Session session, Payment payment) {
        noRecruiting(session);
        fullEnrollment(session);
        payment.complete(session.amount());
    }

    private void fullEnrollment(Session session) {
        if (!session.isFullEnrollment()) {
            throw new EnrollmentMaxExceededException("최대 수강 인원을 초과하였습니다.");
        }
    }
}
