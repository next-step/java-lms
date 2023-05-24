package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.domain.base.BaseDate;
import nextstep.users.domain.NsUser;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;

public class Answer extends BaseDate {

    private Long id;

    private Content content;

    private Question question;

    public Answer() {
    }

    public Answer(Long id, Content content, Question question) {
        questionValidate(question);
        this.id = id;
        this.question = question;
        this.content = content;
    }

    public static Answer toAnswer(NsUser writer, Question question, String contents) {
        return new Answer(null, new Content(contents, writer), question);
    }

    private void questionValidate(Question question) {
        if (question == null) {
            throw new NotFoundException();
        }
    }

    public Long getId() {
        return id;
    }

    public DeleteHistory delete(NsUser writer) throws CannotDeleteException {
        if (!content.isOwner(writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        content.delete();
        return new DeleteHistory(ANSWER, id, writer, now());
    }

    public boolean isDeleted() {
        return content.isDeleted();
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", content=" + content + "]";
    }
}
