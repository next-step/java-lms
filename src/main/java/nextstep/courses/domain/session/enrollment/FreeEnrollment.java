package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeEnrollment implements Enrollment {

    private FreeEnrollment() {

    }

    public static FreeEnrollment from() {
        return new FreeEnrollment();
    }

    @Override
    public void enroll(Session session, NsUser student, Payment payment) {
        validate(session);
        session.addStudent(student);
    }

    private void validate(Session session) {
        noRecruiting(session);
    }
}
