package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.Student;
import nextstep.payments.domain.Payment;

public class FreeEnrollment implements Enrollment {

    private FreeEnrollment() {

    }

    public static FreeEnrollment from() {
        return new FreeEnrollment();
    }

    @Override
    public void enroll(Session session, Student student, Payment payment) {
        validate(session);
        session.addStudent(student);
    }

    private void validate(Session session) {
        noRecruiting(session);
    }
}
