package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    private TimeStamped timeStamped;

    public Question() {
    }

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

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    private void setDeleted() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public Question delete(NsUser loginUser) {
        validate(loginUser);

        this.setDeleted();
        this.answers = answers.delete(loginUser);
        return this;
    }

    private void validate(NsUser loginUser) {
        checkLoginUserIsWriter(loginUser);
    }

    private void checkLoginUserIsWriter(NsUser loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public DeleteHistories toDeleteHistories(NsUser loginUser, LocalDateTime date) {
        validate(loginUser);

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(toDeleteHistory(date));
        deleteHistories.add(answers.toDeleteHistories(date));
        return deleteHistories;
    }

    private DeleteHistory toDeleteHistory(LocalDateTime date) {
        return new DeleteHistory(
                ContentType.QUESTION,
                this.id,
                this.writer,
                date
        );
    }
}
