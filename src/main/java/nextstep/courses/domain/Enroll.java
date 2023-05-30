package nextstep.courses.domain;


import nextstep.users.domain.UserCode;

import javax.validation.constraints.NotNull;

public class Enroll {
    private EnrollId enrollId;
    @NotNull
    private final SessionId sessionId;
    @NotNull
    private final UserCode userCode;
    @NotNull
    private EnrollStatus enrollStatus;

    public Enroll(EnrollId enrollId, SessionId sessionId, UserCode userCode, EnrollStatus enrollStatus) {
        this.enrollId = enrollId;
        this.sessionId = sessionId;
        this.userCode = userCode;
        this.enrollStatus = enrollStatus;
    }

    public static Enroll of(Long sessionId, String userCode) {
        return new Enroll(null, new SessionId(sessionId), new UserCode(userCode),EnrollStatus.SUBMITTED);
    }

    public boolean isEnrolledSession(SessionId sessionId) {
        return sessionId.equals(this.sessionId);
    }

    public void approve() {
        this.enrollStatus = EnrollStatus.APPROVED;
    }

    public void cancel() {
        this.enrollStatus = EnrollStatus.CANCELED;
    }

    public EnrollStatus getEnrollStatus() {
        return enrollStatus;
    }
}
