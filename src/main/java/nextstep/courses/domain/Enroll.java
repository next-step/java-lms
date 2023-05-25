package nextstep.courses.domain;

import nextstep.users.domain.NsUserId;

public class Enroll {
    private EnrolmentId enrolmentId;
    private SessionId sessionId;
    private NsUserId nsUserId;

    public Enroll(Long sessionId, Long nsUserId) {
        this.sessionId = new SessionId(sessionId);
        this.nsUserId = new NsUserId(nsUserId);
    }
}
