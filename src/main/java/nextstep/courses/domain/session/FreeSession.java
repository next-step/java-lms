package nextstep.courses.domain.session;

import nextstep.courses.domain.session.engine.ConcreteSession;
import nextstep.courses.domain.session.image.SessionCoverImage;

public class FreeSession extends ConcreteSession {

    public FreeSession(Long id, Long courseId, SessionCoverImage coverImage, Long enrollmentId, Students students, SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        super(id, courseId, coverImage, new FreeSessionEnrollment(enrollmentId, id, status, capacity, fee), students);
    }

}
