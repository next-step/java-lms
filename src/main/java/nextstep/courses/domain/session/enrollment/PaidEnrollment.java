package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class PaidEnrollment implements Enrollment {

    private final Session session;

    private PaidEnrollment(Session session) {
        this.session = session;
    }

    public static PaidEnrollment from(Session session) {
        return new PaidEnrollment(session);
    }

    @Override
    public void enroll(NsUser student, Payment payment) {
        validate(payment);
        session.addStudent(student);
    }

    private void validate(Payment payment) {
        noRecruiting(session);
        fullEnrollment();
        payment.complete(session.amount());
    }

    private void fullEnrollment() {
        if (!session.isFullEnrollment()) {
            throw new EnrollmentMaxExceededException("최대 수강 인원을 초과하였습니다.");
        }
    }
}
