package nextstep.common.domain;

import java.time.LocalDateTime;

public abstract class BaseDomain {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseDomain(LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
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
