package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public abstract class SoftDeletable {
    private boolean deleted = false;

    protected void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    abstract void deleteBy(NsUser user) throws CannotDeleteException;
}
