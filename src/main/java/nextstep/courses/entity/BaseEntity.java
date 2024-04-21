package nextstep.courses.entity;

import java.time.LocalDateTime;

public abstract class BaseEntity {

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    public BaseEntity() {
        LocalDateTime currentTime = LocalDateTime.now();
        createdAt = currentTime;
        updatedAt = currentTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
