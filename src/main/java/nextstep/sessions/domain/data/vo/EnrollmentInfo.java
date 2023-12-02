package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.exception.SessionsException;

public class EnrollmentInfo {

    private final SessionType sessionType;
    private final SessionState sessionState;

    public EnrollmentInfo(SessionType sessionType, SessionState sessionState) {
        this.sessionType = sessionType;
        this.sessionState = sessionState;
    }

    public void validate(int registrationSize, Payment payment) {
        if (!sessionState.isRecruiting()) {
            throw new SessionsException("모집중이 아닌 강의입니다.");
        }
        sessionType.validateSession(registrationSize, payment);
    }
}
