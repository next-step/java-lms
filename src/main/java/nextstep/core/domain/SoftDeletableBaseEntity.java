package nextstep.core.domain;

public class SoftDeletableBaseEntity extends BaseEntity {
    private boolean deleted = false;

    protected SoftDeletableBaseEntity() {}

    protected SoftDeletableBaseEntity(Long id) {
        super(id);
    }

    public boolean isDeleted() {
        return deleted;
    }

    protected void markDeleted() {
        this.deleted = true;
    }
}
