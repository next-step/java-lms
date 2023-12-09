package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {
    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public BaseEntity(final Long id, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long id() {
        return this.id;
    }
    public LocalDateTime createdAt() {
        return this.createdAt;
    }

    public LocalDateTime updateAt() {
        return this.updatedAt;
    }
}
