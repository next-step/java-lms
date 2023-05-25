package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.generator.SimpleIdGenerator;
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


        if (Objects.isNull(writer)) {
            throw new UnAuthorizedException("작성자에 값이 입력되질 않았어요 :(");
        }

        if (id == 0L) {
            throw new IllegalArgumentException("유요하지 않는 아이디에요 :( [ 입력 값 : " + id + "]");
        }


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

    public static Question of(long id, NsUser writer, String title, String contents, LocalDateTime createdDate) {
        return new Question(id, writer, title, contents, null, createdDate);
    }

    public static Question of(long id, NsUser writer, String title, String contents, LocalDateTime createdDate, DeleteStatus deleted) {

        Question question = new Question(id, writer, title, contents, null, createdDate);

        if (Objects.nonNull(deleted)) {
            question.deleted = deleted;
        }
        return question;
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

    public boolean hasAnotherOwner() {
        return answers.hasAnotherOwner(this.writer);
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

    private static class QuestionRemoveValidator {

        public static void validate(Question question, NsUser requestUser) {
            validateAlreadyDeleted(question);
            validateAuthorization(question, requestUser);
            validateAboutAnswer(question);
        }

        private static void validateAlreadyDeleted(Question question) {
            if (question.isDeleted()) {
                throw new CannotDeleteException("이미 삭제된 질문이에요 :(");
            }
        }

        private static void validateAuthorization(Question question, NsUser requestUser) throws CannotDeleteException {
            if (!question.isOwner(requestUser)) {
                throw new CannotDeleteException("글 작성자만 삭제 가능해요 :( (요청 사용자 : " + requestUser.getUserId() + ")");
            }
        }

        private static void validateAboutAnswer(Question question) {

            if (!question.hasAnswer()) {
                return;
            }

            if (question.hasAnotherOwner()) {
                throw new CannotDeleteException("다른분이 작성한 답변글이 존재해서 삭제 불가능 해요 :(");
            }
        }

    }
}
