package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BaseEntity(Long creatorId) {
        this.creatorId = creatorId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public BaseEntity(Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
