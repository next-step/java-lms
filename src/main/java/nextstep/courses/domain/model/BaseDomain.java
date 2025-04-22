package nextstep.courses.domain.model;

import java.time.LocalDateTime;

public abstract class BaseDomain {
    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    protected BaseDomain() {

    }

    protected BaseDomain(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
