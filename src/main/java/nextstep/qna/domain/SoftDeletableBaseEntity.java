package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public abstract class SoftDeletableBaseEntity extends BaseTimeEntity {

    private Long id;

    private NsUser writer;

    private boolean deleted = false;

    protected SoftDeletableBaseEntity(Long id, NsUser writer) {
        this.id = id;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    protected void markAsDeleted() {
        this.deleted = true;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

}
