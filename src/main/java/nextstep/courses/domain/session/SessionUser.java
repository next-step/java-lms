package nextstep.courses.domain.session;

public class SessionUser {
    private final Long id;
    private final Long sessionId;
    private final Long userId;

    public SessionUser(Long sessionId, Long userId) {
        this(0L, sessionId, userId);
    }

    public SessionUser(Long id, Long sessionId, Long userId) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }
}
