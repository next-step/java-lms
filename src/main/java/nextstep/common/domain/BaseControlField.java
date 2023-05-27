package nextstep.common.domain;

import java.time.LocalDateTime;

public class BaseControlField {
    private Long creatorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseControlField() {
    }

    public BaseControlField(Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    @Override
    public String toString() {
        return "BaseControlField{" +
                "creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
