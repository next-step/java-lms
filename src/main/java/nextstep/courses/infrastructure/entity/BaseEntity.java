package nextstep.courses.infrastructure.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected BaseEntity() {

    }

    protected BaseEntity(Long id, Timestamp createdAt, Timestamp updatedAt) {
        this(id, createdAt.toLocalDateTime(), updatedAt.toLocalDateTime());
    }

    public BaseEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return Timestamp.valueOf(createdAt);
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt.toLocalDateTime();
    }

    public Timestamp getUpdatedAt() {
        return Timestamp.valueOf(updatedAt);
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt.toLocalDateTime();
    }
}
