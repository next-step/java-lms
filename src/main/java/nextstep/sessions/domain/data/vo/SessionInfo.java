package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.SessionState;

public class SessionInfo {

    private final EnrollmentInfo enrollmentInfo;
    private OpenInfo openInfo;

    public SessionInfo(SessionType sessionType, SessionState sessionState) {
        this.enrollmentInfo = new EnrollmentInfo(sessionType, sessionState);
    }

    public SessionInfo(EnrollmentInfo enrollmentInfo, OpenInfo openInfo) {
        this.enrollmentInfo = enrollmentInfo;
        this.openInfo = openInfo;
    }

    public void validateEnrollment(int size, Payment payment) {
        enrollmentInfo.validate(size, payment);
    }

    public EnrollmentInfo enrollmentInfo() {
        return enrollmentInfo;
    }

    public OpenInfo openInfo() {
        return openInfo;
    }


}
