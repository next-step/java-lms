package nextstep.session.dto;

import java.time.LocalDateTime;

public class StudentDto {

    private final long id;
    private final long sessionId;
    private final String userId;
    private final boolean deleted;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    public StudentDto(
            long id, long sessionId, String userId, boolean deleted,
            LocalDateTime createdAt, LocalDateTime lastModifiedAt
    ) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
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
