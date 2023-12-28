package nextstep.courses.domain;

import java.time.LocalDateTime;

public abstract class AuditInfo {
    protected final LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    protected AuditInfo(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return LocalDateTime.of(updatedAt.toLocalDate(), updatedAt.toLocalTime());
    }
}
