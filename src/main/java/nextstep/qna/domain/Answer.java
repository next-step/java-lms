package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends Post{

    private Question question;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public boolean isDeleted() {
        return deleted;
    }

    private boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete(NsUser loginUser) {
        authorityValidation(loginUser);
        this.deleted = true;
    }

    private void authorityValidation(NsUser loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Answer [id=" + this.id + ", writer=" + writer + ", contents=" + contents + "]";
    }

    @Override
    public DeleteHistory makeDeleteHistory() {
        return new DeleteHistory(ContentType.ANSWER, id, writer, LocalDateTime.now());
    }
}
