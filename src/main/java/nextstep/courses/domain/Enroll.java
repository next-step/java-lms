package nextstep.courses.domain;

import nextstep.users.domain.NsUserId;

public class Enroll {
    private EnrollId enrollId;
    private final SessionId sessionId;
    private final NsUserId nsUserId;

    public Enroll(Long sessionId, Long nsUserId) {
        this.sessionId = new SessionId(sessionId);
        this.nsUserId = new NsUserId(nsUserId);
    }
}
