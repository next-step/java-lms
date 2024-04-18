package nextstep.courses.infrastructure.entity;

import java.time.LocalDateTime;

public class BaseEntity {

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public BaseEntity() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public BaseEntity(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
