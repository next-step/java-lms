package nextstep.courses.domain.session;

import nextstep.courses.domain.session.engine.ConcreteSession;
import nextstep.payments.domain.Payment;

public class FreeSession extends ConcreteSession {

    public FreeSession(Long id, Long courseId, SessionCoverImage coverImage, FreeSessionEnrollment enrollment, Students students) {
        super(id, courseId, coverImage, enrollment, students);
    }

    @Override
    public void enroll(Student student, Payment payment) {
        enrollment.satisfy(students, payment);
        students.add(student);
    }

}
