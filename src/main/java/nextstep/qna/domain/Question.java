package nextstep.qna.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.qna.domain.validate.QuestionRemoveValidator;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class Question {

    private final long id;
    private final NsUser writer;
    private Answers answers;
    private final LocalDateTime createdDate;

    private String title;
    private String contents;

    private DeleteStatus deleted = DeleteStatus.NOT_DELETED;
    private LocalDateTime updateAt;

    private Question(long id, NsUser writer, String title, String contents, Answers answers, LocalDateTime createdDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.answers = answers;
    }

    public static Question of(NsUser writer, String title, String contents) {
        long id = SimpleIdGenerator.getAndIncrement(Question.class);
        return new Question(id, writer, title, contents, Answers.create(), LocalDateTime.now());
    }

    public static Question of(long id, NsUser writer, String title, String contents, Answers answers, LocalDateTime createdDate) {
        return new Question(id, writer, title, contents, answers, createdDate);
    }

    public Question changeTitle(String title) {
        this.title = title;
        this.updateAt = LocalDateTime.now();
        return this;
    }

    public Question changeContents(String contents) {
        this.contents = contents;
        this.updateAt = LocalDateTime.now();
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public NsUser getWriter() {
        return writer;
    }

    public boolean isOwner(NsUser loginUser) {

        if (Objects.isNull(loginUser)) {
            throw new IllegalStateException("인가 되지 않은 사용자 에요 :(");
        }

        return writer.equals(loginUser);
    }

    public Question loadAnswers(Answers answers) {
        this.answers = answers;
        return this;
    }

    public DeleteHistories remove(NsUser requestUser) {
        QuestionRemoveValidator.validate(this, requestUser);

        this.deleted = DeleteStatus.DELETED;
        this.updateAt = LocalDateTime.now();

        DeleteHistories deleteHistories = DeleteHistories.create();
        deleteHistories.add(DeleteHistory.of(ContentType.QUESTION, this.id, this.writer));

        if (hasAnswer()) {
            deleteHistories.concat(answers.removeAll());
        }
        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted.isDeleted();
    }

    public boolean hasAnswer() {

        if (Objects.nonNull(answers)) {
            return answers.hasAnswers();
        }

        return false;
    }

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && Objects.equals(writer, question.writer) && Objects.equals(answers, question.answers) && Objects.equals(title, question.title) && Objects.equals(contents, question.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, answers, title, contents);
    }
}
