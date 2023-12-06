package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.exception.SessionsException;

public class EnrollmentInfo {

    private final SessionType sessionType;
    private SessionState sessionState;
    private NewSessionState newSessionState;

    public EnrollmentInfo(SessionType sessionType, SessionState sessionState) {
        this.sessionType = sessionType;
        this.sessionState = sessionState;
    }

    public EnrollmentInfo(SessionType sessionType, NewSessionState newSessionState) {
        this.sessionType = sessionType;
        this.newSessionState = newSessionState;
    }

    public void validate(int registrationSize, Payment payment) {
        if (!sessionState.isRecruiting()) {
            throw new SessionsException("모집중이 아닌 강의입니다.");
        }
        newSessionState.validateState();
        sessionType.validateSession(registrationSize, payment);
    }

    public SessionType sessionType() {
        return sessionType;
    }

    public SessionState sessionState() {
        return sessionState;
    }

    public NewSessionState newSessionState() {
        return newSessionState;
    }
}
