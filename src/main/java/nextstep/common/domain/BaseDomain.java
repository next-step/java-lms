package nextstep.common.domain;

import java.time.LocalDateTime;

public class BaseDomain {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseDomain() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public BaseDomain(LocalDateTime createdAt, LocalDateTime updatedAt) {
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
