package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {
    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseEntity(long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public long id() {
        return id;
    }

}
