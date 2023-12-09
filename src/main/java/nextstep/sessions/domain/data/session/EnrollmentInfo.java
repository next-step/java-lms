package nextstep.sessions.domain.data.session;

import nextstep.payments.domain.Payment;

public class EnrollmentInfo {

    private final SessionType sessionType;
    private final SessionState sessionState;

    public EnrollmentInfo(SessionType sessionType, SessionState sessionState) {
        this.sessionType = sessionType;
        this.sessionState = sessionState;
    }

    public void validateEnrollment(int registrationSize, Payment payment) {
        sessionState.validateState();
        sessionType.validateSession(registrationSize, payment);
    }

    public String paidType() {
        return sessionType.paidType();
    }

    public long fee() {
        return sessionType.fee();
    }

    public int capacity() {
        return sessionType.capacity();
    }

    public String sessionRunningState() {
        return sessionState.sessionRunningStateName();
    }

    public String sessionRecruitingState() {
        return sessionState.sessionRecruitingStateName();
    }
}
