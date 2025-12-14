package nextstep.common.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class SoftDeletableBaseEntity {
    private Long id;
    private boolean deleted;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    protected SoftDeletableBaseEntity(Long id) {
        this(id, false, LocalDateTime.now(), LocalDateTime.now());
    }

    protected SoftDeletableBaseEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, false, createdAt, updatedAt);
    }

    protected SoftDeletableBaseEntity(Long id, boolean deleted, LocalDateTime createdDate,
            LocalDateTime updatedDate) {
        this.id = id;
        this.deleted = deleted;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    protected boolean isDeleted() {
        return this.deleted;
    }

    public void updateDeleted() {
        this.deleted = true;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SoftDeletableBaseEntity that = (SoftDeletableBaseEntity) o;
        return deleted == that.deleted
                && Objects.equals(id, that.id)
                && Objects.equals(createdDate, that.createdDate)
                && Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, createdDate, updatedDate);
    }
}
