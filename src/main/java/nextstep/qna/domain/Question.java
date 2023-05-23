package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Question {
    private final Long id;

    private final String title;

    private final String contents;

    private final NsUser writer;

    private final Answers answers = new Answers();

    private boolean deleted = false;

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers.getAnswers();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public DeleteHistories delete(NsUser loginUser) {
        if (!this.writer.matchUser(loginUser)) {
            throw new CannotDeleteException("로그인 사용자와 질문한 사람이 같지 않습니다.");
        }
        this.setDeleted(true);
        return documentedDeleteHistories();
    }

    private DeleteHistories documentedDeleteHistories() {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(DeleteHistory.createQuestion(this.id, this.writer));

        answers.forEach(answer -> deleteHistories.add(answer.delete()));
        return deleteHistories;
    }
}
