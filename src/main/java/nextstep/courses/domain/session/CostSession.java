package nextstep.courses.domain.session;

import nextstep.courses.domain.session.engine.ConcreteSession;

public class CostSession extends ConcreteSession {

    public CostSession(Long id, Long courseId, SessionCoverImage coverImage, Long enrollmentId, Students students, SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        super(id, courseId, coverImage, new CostSessionEnrollment(enrollmentId, id, status, capacity, fee), students);
    }

}
