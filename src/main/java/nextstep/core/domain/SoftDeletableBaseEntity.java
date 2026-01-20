package nextstep.core.domain;

import java.time.LocalDateTime;

public class SoftDeletableBaseEntity extends BaseEntity {
    private boolean deleted = false;

    protected SoftDeletableBaseEntity() {}

    protected SoftDeletableBaseEntity(Long id) {
        super(id);
    }

    protected SoftDeletableBaseEntity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
    }

    public boolean isDeleted() {
        return deleted;
    }

    protected void markDeleted() {
        this.deleted = true;
    }
}
