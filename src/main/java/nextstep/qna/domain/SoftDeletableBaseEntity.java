package nextstep.qna.domain;

public class SoftDeletableBaseEntity {
    private final BaseEntity baseEntity;
    private boolean deleted = false;

    public SoftDeletableBaseEntity(Long id) {
        this(new BaseEntity(id));
    }

    public SoftDeletableBaseEntity(BaseEntity entity) {
        this.baseEntity = entity;
    }

    public Long getId() {
        return baseEntity.getId();
    }

    protected void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
