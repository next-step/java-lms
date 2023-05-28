package nextstep.courses.domain;


import nextstep.users.domain.UserCode;

public class Enroll {
    private EnrollId enrollId;
    private final SessionId sessionId;
    private final UserCode userCode;

    public Enroll(Long sessionId, String userCode) {
        this.sessionId = new SessionId(sessionId);
        this.userCode = new UserCode(userCode);
    }
}
