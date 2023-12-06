package nextstep.sessions.domain.data.vo;

import nextstep.payments.domain.Payment;

public class SessionInfo {

    private final EnrollmentInfo enrollmentInfo;
    private OpenInfo openInfo;

    public SessionInfo(SessionType sessionType, NewSessionState newSessionState) {
        this.enrollmentInfo = new EnrollmentInfo(sessionType, newSessionState);
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
