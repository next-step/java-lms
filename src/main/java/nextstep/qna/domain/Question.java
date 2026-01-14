package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;
    private QuestionContent content;
    private Answers answers;
    private QuestionState state;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.content = new QuestionContent(title, contents);
        this.answers = Answers.empty();
        this.state = new QuestionState(writer);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return content.getTitle();
    }

    public String getContents() {
        return content.getContents();
    }

    public NsUser getWriter() {
        return state.getWriter();
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return state.isOwner(loginUser);
    }

    public boolean isDeleted() {
        return state.isDeleted();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + getTitle() + ", contents=" + getContents() + ", writer=" + getWriter() + "]";
    }

    public List<DeleteHistory> deleteBy(NsUser loginUser, LocalDateTime now)
        throws CannotDeleteException {

        state.validateDeletePermission(loginUser, answers);

        state.setDeleted(true);

        List<DeleteHistory> histories = new ArrayList<>();
        histories.add(new DeleteHistory(ContentType.QUESTION, this.id, state.getWriter(), now));

        answers.deleteAll();
        histories.addAll(answers.createDeleteHistories(now));

        return histories;
    }
}
