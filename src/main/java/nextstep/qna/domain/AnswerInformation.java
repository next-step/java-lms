package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class AnswerInformation {

    private final String contents;

    private final NsUser writer;

    private final boolean deleted;

    private final Times times;

    public AnswerInformation(String contents, NsUser writer, boolean deleted, Times times) {
        this.contents = contents;
        this.writer = writer;
        this.deleted = deleted;
        this.times = times;
    }

    public void validateWriter(NsUser loginUser) {
        if (isNotSameWriter(loginUser)) {
            throw new CannotDeleteException("답변을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isNotSameWriter(NsUser loginUser) {
        return !this.writer.equals(loginUser);
    }

    public void validateDeleted() {
        if (this.deleted) {
            throw new CannotDeleteException("이미 삭제된 답변입니다.");
        }
    }

    public AnswerInformation delete() {
        return new AnswerInformation(this.contents, this.writer, true, this.times);
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}
