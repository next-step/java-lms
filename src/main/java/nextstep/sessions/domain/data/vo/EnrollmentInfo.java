package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;

public class EnrollmentInfo {

    private final SessionType sessionType;
    private final NewSessionState newSessionState;

    public EnrollmentInfo(SessionType sessionType, NewSessionState newSessionState) {
        this.sessionType = sessionType;
        this.newSessionState = newSessionState;
    }

    public void validate(int registrationSize, Payment payment) {
        newSessionState.validateState();
        sessionType.validateSession(registrationSize, payment);
    }

    public SessionType sessionType() {
        return sessionType;
    }

    public NewSessionState newSessionState() {
        return newSessionState;
    }
}
