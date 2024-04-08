package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionException;

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

    public boolean hasCapacity() {
        return capacity.hasCapacity();
    }

    public SessionCapacity getCapacity() {
        return capacity;
    }

    public boolean matchFee(Long amount) {
        return fee.matchFee(amount);
    }

    public SessionFee getFee() {
        return fee;
    }

    public void enroll() throws SessionException {
        validateEnrollmentPossible();
        capacity.increaseCurrentCapacity();
    }

    private void validateEnrollmentPossible() throws SessionException {
        enrollmentConditions.validateSatisfy(this);
        sessionStatus.validateCanEnrollment();
    }
}
