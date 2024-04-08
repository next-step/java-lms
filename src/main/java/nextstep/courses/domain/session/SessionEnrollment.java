package nextstep.courses.domain.session;

import nextstep.courses.domain.session.condition.SessionEnrollmentConditions;
import nextstep.courses.exception.SessionException;
import nextstep.users.domain.NsUser;

public class SessionEnrollment {

    private final Long id;
    private final Long sessionId;
    private final SessionCapacity capacity;
    private final SessionStatus sessionStatus;
    private final SessionEnrollmentConditions enrollmentConditions;
    private final SessionFee fee;

    public SessionEnrollment(Long id, Long sessionId, SessionCapacity capacity, SessionStatus sessionStatus, SessionEnrollmentConditions enrollmentConditions, SessionFee fee) {
        this.id = id;
        this.sessionId = sessionId;
        this.capacity = capacity;
        this.sessionStatus = sessionStatus;
        this.enrollmentConditions = enrollmentConditions;
        this.fee = fee;
    }

    public void enroll(NsUser user) throws SessionException {
        validateSessionEnrollment();
        capacity.addUser(user);
    }

    private void validateSessionEnrollment() throws SessionException {
        enrollmentConditions.validateSatisfy();
        sessionStatus.validateCanEnrollment();
    }
}
