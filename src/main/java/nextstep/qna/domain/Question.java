package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question extends Content {
    private String title;

    private Answers answers = new Answers();

    public Question() {
        super();
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        super(id, writer, contents);
        this.title = title;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public List<DeleteHistory> delete(NsUser loginUser) throws CannotDeleteException {
        validateOwner(loginUser);
        this.deleted = true;

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(getDeleteHistory());

        List<DeleteHistory> answerDeleteHistories = this.answers.deleteAnswers(loginUser);
        deleteHistories.addAll(answerDeleteHistories);

        return deleteHistories;
    }

    protected DeleteHistory getDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
