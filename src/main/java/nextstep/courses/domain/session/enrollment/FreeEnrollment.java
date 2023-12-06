package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class FreeEnrollment implements Enrollment {

    private final Session session;

    private FreeEnrollment(Session session) {
        this.session = session;
    }

    public static FreeEnrollment from(Session session) {
        return new FreeEnrollment(session);
    }

    @Override
    public void enroll(NsUser student, Payment payment) {
        validate();
        session.addStudent(student);
    }

    private void validate() {
        noRecruiting(session);
    }
}
