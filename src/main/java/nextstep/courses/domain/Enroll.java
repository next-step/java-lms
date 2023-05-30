package nextstep.courses.domain;


import nextstep.users.domain.UserCode;

public class Enroll {
    private EnrollId enrollId;
    private final SessionId sessionId;
    private final UserCode userCode;
    private EnrollStatus enrollStatus;

    public Enroll(EnrollId enrollId, SessionId sessionId, UserCode userCode) {
        this.enrollId = enrollId;
        this.sessionId = sessionId;
        this.userCode = userCode;
    }

    public static Enroll of(Long sessionId, String userCode) {
        return new Enroll(null, new SessionId(sessionId), new UserCode(userCode));
    }

    public boolean isEnrolledSession(SessionId sessionId) {
        return sessionId.equals(this.sessionId);
    }

    public void approve() {
    }


    public void cancel() {
    }

    public EnrollStatus getEnrollStatus() {
        return enrollStatus;
    }
}
