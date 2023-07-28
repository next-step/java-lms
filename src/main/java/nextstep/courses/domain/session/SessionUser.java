package nextstep.courses.domain.session;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionUser that = (SessionUser) o;
        return Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, userId);
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }
}
