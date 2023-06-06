package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class BaseEntity {
    protected Long id;

    protected String title;

    protected Long creatorId;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(creatorId, that.creatorId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creatorId, createdAt, updatedAt);
    }
}
