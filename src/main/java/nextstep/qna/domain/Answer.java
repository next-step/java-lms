package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer extends BaseTimeEntity {

    private AnswerBody answerBody;
    private Deleted deleted;

    private Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this(id, writer, question, contents, new Deleted());
    }

    public Answer(Long id, NsUser writer, Question question, String contents, Deleted deleted) {
        this(new AnswerBody(id, writer, question, contents), deleted);
    }

    public Answer(AnswerBody answerBody, Deleted deleted) {
        this.answerBody = answerBody;
        this.deleted = deleted;
    }

    public void canDelete(NsUser loginUser) throws CannotDeleteException {
        if (isOwner(loginUser)) {
            return;
        }

        throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    public boolean isOwner(NsUser writer) {
        return this.answerBody.isOwner(writer);
    }

    public Long getId() {
        return this.answerBody.getId();
    }

    public AnswerBody getAnswerBody() {
        return this.answerBody;
    }

    public NsUser getWriter() {
        return this.answerBody.getWriter();
    }

    public String getContents() {
        return this.answerBody.getContents();
    }

    public void toQuestion(Question question) {
        this.answerBody.toQuestion(question);
    }

    public boolean isDeleted() {
        return this.deleted.isDeleted();
    }

    public void delete() {
        this.deleted.delete();
    }

    public DeleteHistory toDeleteHistory() {
        delete();
        return new DeleteHistory(ContentType.ANSWER, getId(), getWriter(), LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + getWriter() + ", contents=" + getContents() + "]";
    }
}
