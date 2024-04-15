package nextstep.session.dto;

import nextstep.session.type.SessionApprovedType;

import java.time.LocalDateTime;

public class StudentVO {

    private final long id;
    private final long sessionId;
    private final String userId;
    private final String approved;
    private final boolean deleted;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    public StudentVO(
            long id, long sessionId, String userId, String approved, boolean deleted,
            LocalDateTime createdAt, LocalDateTime lastModifiedAt
    ) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.approved = approved;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public long getId() {
        return id;
    }

    public long getSessionId() {
        return sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getApproved() {
        return approved;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }
}
