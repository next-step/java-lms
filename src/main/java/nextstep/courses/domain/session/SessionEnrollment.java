package nextstep.courses.domain.session;

import nextstep.courses.domain.session.condition.SessionConditions;
import nextstep.courses.exception.SessionException;
import nextstep.users.domain.NsUser;

public class SessionEnrollment {

    private final Long id;
    private final Long sessionId;
    private final SessionCapacity capacity;
    private final SessionStatus status;
    private final SessionConditions conditions;
    private final SessionFee fee;

    public SessionEnrollment(Long id, Long sessionId, SessionCapacity capacity, SessionStatus status, SessionConditions conditions, SessionFee fee) {
        this.id = id;
        this.sessionId = sessionId;
        this.capacity = capacity;
        this.status = status;
        this.conditions = conditions;
        this.fee = fee;
    }

    public void enroll(NsUser user) throws SessionException {
        validateSessionEnrollment();
        capacity.addUser(user);
    }

    private void validateSessionEnrollment() throws SessionException {
        conditions.satisfy();
        status.validateCanEnrollment();
    }
}
