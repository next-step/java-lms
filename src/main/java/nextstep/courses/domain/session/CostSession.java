package nextstep.courses.domain.session;

import nextstep.courses.domain.session.engine.ConcreteSession;
import nextstep.payments.domain.Payment;

public class CostSession extends ConcreteSession {

    protected CostSession(Long id, Long courseId, SessionCoverImage coverImage, CostSessionEnrollment enrollment, Students students) {
        super(id, courseId, coverImage, enrollment, students);
    }

    @Override
    public void enroll(Student student, Payment payment) {

    }
}
