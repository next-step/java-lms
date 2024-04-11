package nextstep.courses.domain;

import nextstep.courses.domain.engine.ConcreteSession;
import nextstep.courses.domain.enrollment.*;
import nextstep.courses.domain.image.SessionCoverImage;

public class FreeSession extends ConcreteSession {

    public FreeSession(Long id, Long courseId, SessionCoverImage coverImage, Long enrollmentId, Students students, SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        super(id, courseId, coverImage, new FreeSessionEnrollment(enrollmentId, id, status, capacity, fee), students);
    }

}
