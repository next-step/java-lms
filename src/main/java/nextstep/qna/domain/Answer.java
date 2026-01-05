package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.ContentNotDeletedException;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Answer extends Content {
    private Question question;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        super(id, writer, contents);
        if (question == null) {
            throw new NotFoundException();
        }
        this.question = question;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete(NsUser writer) throws CannotDeleteException {
        if (!isOwner(writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        info.delete();
    }

    public DeleteHistory history() throws ContentNotDeletedException {
        if (!info.isDeleted()) {
            throw new ContentNotDeletedException("아직 삭제되지 않은 답변입니다.");
        }

        return new DeleteHistory(ContentType.ANSWER, id, info.getWriter(), LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + info + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answer answer = (Answer) o;
        return Objects.equals(question, answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), question);
    }
}
