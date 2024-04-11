package nextstep.courses.domain;

import nextstep.courses.domain.engine.ConcreteSession;
import nextstep.courses.domain.enrollment.*;
import nextstep.courses.domain.image.SessionCoverImage;

public class FreeSession extends ConcreteSession {

    public FreeSession(Long id, Long courseId, SessionCoverImage coverImage, Long enrollmentId, SessionStatus status) {
        super(id, courseId, coverImage, new FreeSessionEnrollment(enrollmentId, id, status));
    }

}
