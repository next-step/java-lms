package nextstep.qna.domain;

public class Deleted {
    private boolean deleted;

    public Deleted() {
        this(false);
    }

    public Deleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        this.deleted = Boolean.TRUE;
    }
}
