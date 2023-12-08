package nextstep.sessions.domain.data.vo;

import java.time.LocalDateTime;

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
    
    public String paidType() {
        return enrollmentInfo.paidType();
    }

    public long fee() {
        return enrollmentInfo.fee();
    }

    public int capacity() {
        return enrollmentInfo.capacity();
    }

    public String sessionRunningState() {
        return enrollmentInfo.sessionRunningState();
    }

    public String sessionRecruitingState() {
        return enrollmentInfo.sessionRecruitingState();
    }

    public LocalDateTime startDate() {
        return openInfo.startDate();
    }

    public LocalDateTime endDate() {
        return openInfo.endDate();
    }
}
