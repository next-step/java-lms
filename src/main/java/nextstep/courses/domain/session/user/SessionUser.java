package nextstep.courses.domain.session.user;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionUser {
    private Long id;
    private Long sessionId;
    private Long userId;
    private SessionUserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SessionUser(Long sessionId, Long userId) {
        this(0L, sessionId, userId, SessionUserStatus.AWAITING, LocalDateTime.now(), null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionUser that = (SessionUser) o;
        return Objects.equals(sessionId, that.sessionId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, userId);
    }

    public SessionUser(Long id, Long sessionId, Long userId, SessionUserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void accessSession() {
        this.status = SessionUserStatus.ACCESS;
    }

    public SessionUserStatus getStatus() {
        return this.status;
    }

    public void cancelSession() {
        this.status = SessionUserStatus.CANCEL;
    }
}
