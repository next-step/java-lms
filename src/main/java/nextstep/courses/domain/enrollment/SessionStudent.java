package nextstep.courses.domain.enrollment;

import nextstep.users.domain.NsUser;

public class SessionStudent {

    private Long id;
    private Long sessionId;
    private Long nsUserId;

    public static SessionStudent from(Long sessionId, NsUser nsUser) {
        return new SessionStudent(sessionId, nsUser.getId());
    }

    public SessionStudent(Long sessionId, Long nsUserId) {
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }
}
