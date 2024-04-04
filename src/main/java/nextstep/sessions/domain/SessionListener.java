package nextstep.sessions.domain;

public class SessionListener {
    private final Long sessionId;
    private final Long nsUserId;

    public SessionListener(Long sessionId, Long nsUserId) {
        if (sessionId == null) {
            throw new IllegalArgumentException();
        }

        if (nsUserId == null) {
            throw new IllegalArgumentException();
        }

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
