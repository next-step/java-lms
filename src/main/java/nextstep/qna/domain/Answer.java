package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answer {

    private Question question;

    private Contents contents;

    private boolean deleted = false;

    public Answer(Question question, Contents contents) {

        if(contents.getWriter().isBlank()) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.question = question;
        this.contents = contents;
    }

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public DeleteHistory delete(String loginUserId) throws CannotDeleteException {
        if (contents.isNotOwner(loginUserId)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, contents, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Answer [id=" + contents.getId() + ", writer=" + contents.getWriter() + ", contents=" + contents + "]";
    }
}
