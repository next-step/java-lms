package nextstep.courses.domain.session.engine;

import nextstep.courses.domain.session.SessionCapacity;
import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.domain.session.SessionStatus;

public abstract class ConcreteSessionEnrollment implements SessionEnrollment {

    protected final Long id;
    protected final Long sessionId;
    protected final SessionStatus status;
    protected final SessionCapacity capacity;
    protected final SessionFee fee;

    protected ConcreteSessionEnrollment(Long id, Long sessionId, SessionStatus status, SessionCapacity capacity, SessionFee fee) {
        this.id = id;
        this.sessionId = sessionId;
        this.status = status;
        this.capacity = capacity;
        this.fee = fee;
    }

}
