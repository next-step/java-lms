package nextstep.courses.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    public LocalDateTime createdAt = LocalDateTime.now();

    public LocalDateTime updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
