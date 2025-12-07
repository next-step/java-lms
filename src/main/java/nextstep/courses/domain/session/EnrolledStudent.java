package nextstep.courses.domain.session;

public class EnrolledStudent {
    private final Long sessionId;
    private final Long nsUserId;

    public EnrolledStudent(Long sessionId, Long nsUserId) {
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
