package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {
    private final Long creatorId;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public BaseEntity(Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long creatorId() {
        return creatorId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
