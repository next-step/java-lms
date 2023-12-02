package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class QuestionInformation {

    private final QuestionTexts texts;

    private final NsUser writer;

    private final Times times;

    private final boolean deleted;

    public QuestionInformation(QuestionTexts texts, NsUser writer, Times times, boolean deleted) {
        this.texts = texts;
        this.writer = writer;
        this.times = times;
        this.deleted = deleted;
    }

    public void validateWriter(NsUser loginUser) {
        if (isNotSameWriter(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isNotSameWriter(NsUser loginUser) {
        return !this.writer.equals(loginUser);
    }

    public void validateDeleted() {
        if (this.deleted) {
            throw new CannotDeleteException("이미 삭제된 질문입니다.");
        }
    }

    public QuestionInformation delete() {
        return new QuestionInformation(this.texts, this.writer, this.times, true);
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}
