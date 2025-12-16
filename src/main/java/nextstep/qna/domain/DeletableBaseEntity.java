package nextstep.qna.domain;

public class DeletableBaseEntity extends BaseEntity {

    private boolean deleted = false;

    protected void delete() {
        this.deleted = true;
    }

    protected boolean isDeleted() {
        return this.deleted;
    }
}
