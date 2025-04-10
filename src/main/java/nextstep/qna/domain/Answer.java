package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answer extends Content {
    private Question question;

    public Answer() {
        super();
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        super(id, writer, contents);

        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.question = question;
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);
        this.deleted = true;
        DeleteHistory deleteHistory = getDeleteHistory();
        return List.of(deleteHistory);
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    protected DeleteHistory getDeleteHistory() {
        return new DeleteHistory(ContentType.ANSWER, this.id, this.writer, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
