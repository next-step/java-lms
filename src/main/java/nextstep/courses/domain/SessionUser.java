package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionUser {
    private Long id;
    private Long sessionId;
    private String userId;
    private AuditTimestamp auditTimestamp;

    public SessionUser(Long sessionId, String userId) {
        this(0L, sessionId, userId, LocalDateTime.now());
    }

    public SessionUser(Long id, Long sessionId, String userId, LocalDateTime createdAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.auditTimestamp = new AuditTimestamp(createdAt, null);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionUser that = (SessionUser) o;
        return Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getSessionId() {
        return sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return auditTimestamp.getCreatedAt();
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", userId=" + userId +
                ", auditTimestamp=" + auditTimestamp +
                '}';
    }
}
