package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answer extends BaseEntity {

    private Long id;

    private AnswerBody answerBody;

    private boolean deleted = false;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, new AnswerBody(writer, question, contents));
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this(id, new AnswerBody(writer, question, contents));
    }


    public Answer(Long id, AnswerBody answerBody) {
        this.id = id;
        this.answerBody = answerBody;
    }

    public void isOwner(NsUser loginUser) throws CannotDeleteException {
        answerBody.checkOwner(loginUser);
    }

    public DeleteHistory delete(final LocalDateTime now) {
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, id, answerBody.getWriter(), now);
    }

    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public NsUser getWriter() {
        return answerBody.getWriter();
    }

    public void toQuestion(Question question) {
        answerBody.addQuestion(question);
    }

    @Override
    public String toString() {
        return "Answer{" +
            "id=" + id +
            ", answerBody=" + answerBody +
            ", deleted=" + deleted +
            '}';
    }
}

