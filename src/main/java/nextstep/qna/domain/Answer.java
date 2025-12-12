package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends SoftDeletableBaseEntity {

    private Question question;

    private String contents;

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        super(id, writer);
        this.question = question;
        this.contents = contents;
    }

    public static Answer create(NsUser writer, Question question, String contents) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        return new Answer(null, writer, question, contents);
    }

    public String getContents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete(NsUser user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        markAsDeleted();
    }

    public List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(
                new DeleteHistory(ContentType.ANSWER, getId(), getWriter(), LocalDateTime.now()));
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + getWriter() + ", contents=" + contents + "]";
    }
}
