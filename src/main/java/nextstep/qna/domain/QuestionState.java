package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class QuestionState {
    private final NsUser writer;
    private boolean deleted;
    private final LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public QuestionState(NsUser writer) {
        this.writer = writer;
        this.deleted = false;
        this.createdDate = LocalDateTime.now();
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public void validateDeletePermission(NsUser loginUser, Answers answers) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        if (answers.isEmpty()) {
            return;
        }

        answers.validateAllOwnedBy(loginUser);
    }
}
