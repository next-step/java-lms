package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BaseEntity() {
        this.createdAt = LocalDateTime.now();
    }

    public BaseEntity(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
}
