package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Application {
    private static final String APPROVAL_NOT_ALLOWED = "승인할 수 없는 상태입니다.";
    private static final String REJECT_NOT_ALLOWED = "거절할 수 없는 상태입니다.";

    private final Long id;
    private final Long sessionId;
    private final Long userId;
    private ApplicationStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Application(Long sessionId, Long userId) {
        this(null, sessionId, userId, ApplicationStatus.PENDING, LocalDateTime.now(), null);
    }

    public Application(Long id, Long sessionId, Long userId, ApplicationStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Application approve() {
        if (status != ApplicationStatus.PENDING) {
            throw new IllegalStateException(APPROVAL_NOT_ALLOWED);
        }
        return new Application(id, sessionId, userId, ApplicationStatus.APPROVED, createdAt, LocalDateTime.now());
    }

    public Application reject() {
        if (status != ApplicationStatus.PENDING) {
            throw new IllegalStateException(REJECT_NOT_ALLOWED);
        }
        return new Application(id, sessionId, userId, ApplicationStatus.REJECTED, createdAt, LocalDateTime.now());
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(userId, that.userId) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, userId, status);
    }
}
