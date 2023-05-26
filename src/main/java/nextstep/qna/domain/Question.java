package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question extends BaseEntity {

    private Long id;

    private QuestionBody questionBody;

    private Answers answers;

    private boolean deleted = false;


    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, new QuestionBody(writer, title, contents));
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this(id, new QuestionBody(writer, title, contents));
    }

    public Question(Long id, QuestionBody questionBody) {
        this.id = id;
        this.questionBody = questionBody;
    }

    public void addAnswer(Answer answer) {
        if (answers == null) {
            this.answers = new Answers();
        }
        answer.toQuestion(this);
        answers.add(answer);
    }

    public void checkUserOfAnswers(final NsUser loginUser) throws CannotDeleteException {
        answers.validateUser(loginUser);
    }

    public void isOwner(NsUser loginUser) throws CannotDeleteException {
        questionBody.checkOwner(loginUser);
    }

    public List<DeleteHistory> delete(final LocalDateTime now) {
        this.deleted = true;

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(
            new DeleteHistory(ContentType.QUESTION, id, questionBody.getWriter(), now)
        );

        deleteAnswers(now, deleteHistories);
        return deleteHistories;
    }

    private void deleteAnswers(final LocalDateTime now, final List<DeleteHistory> deleteHistories) {
        answers.delete(now, deleteHistories);
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return questionBody.getWriter();
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", questionBody=" + questionBody +
            ", answers=" + answers +
            ", deleted=" + deleted +
            '}';
    }
}
