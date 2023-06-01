package nextstep.courses.domain;

import java.time.LocalDateTime;

public class AuditTimestamp {
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AuditTimestamp() {
        this(LocalDateTime.now(), null);
    }

    public AuditTimestamp(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
