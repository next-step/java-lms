package nextstep.common.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    protected BaseEntity(Long id, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    protected Long getId() {
        return id;
    }

    protected LocalDateTime getCreatedDate() {
        return createdDate;
    }

    protected LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(createdDate, that.createdDate)
                && Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
