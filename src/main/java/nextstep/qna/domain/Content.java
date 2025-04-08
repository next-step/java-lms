package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public abstract class Content {
    protected Long id;

    protected NsUser writer;

    protected String contents;

    protected boolean deleted = false;

    protected AuditInfo auditInfo = new AuditInfo();

    public Content() {
    }

    public Content(Long id, NsUser writer, String contents) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }

    public abstract List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException;

    protected abstract DeleteHistory getDeleteHistory();

    protected boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    protected boolean isNotOwner(NsUser loginUser) {
        return !isOwner(loginUser);
    }

    protected void validateOwner(NsUser loginUser) throws CannotDeleteException {
        if (isNotOwner(loginUser)) {
            throw new CannotDeleteException("소유자가 아니라 삭제할 수 없습니다.");
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Long getId() {
        return id;
    }
}
