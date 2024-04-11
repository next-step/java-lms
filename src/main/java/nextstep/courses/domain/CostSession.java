package nextstep.courses.domain;

import nextstep.courses.domain.engine.ConcreteSession;
import nextstep.courses.domain.enrollment.*;
import nextstep.courses.domain.image.SessionCoverImage;

public class CostSession extends ConcreteSession {

    public CostSession(Long id, Long courseId, SessionCoverImage coverImage, Long enrollmentId, SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        super(id, courseId, coverImage, new CostSessionEnrollment(enrollmentId, id, status, capacity, fee));
    }

}
