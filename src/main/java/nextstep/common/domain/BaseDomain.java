package nextstep.common.domain;

import java.time.LocalDateTime;

public class BaseDomain {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseDomain() {
    }

    public BaseDomain(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
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
