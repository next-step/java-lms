package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Question {

    private final long id;
    private final String writerId;
    private final LocalDateTime createdDate;

    private String title;
    private String contents;

    private DeleteStatus deleted = DeleteStatus.NOT_DELETED;
    private LocalDateTime updateAt;

    private Question(long id, String writerId, String title, String contents, LocalDateTime createdDate) {


        if (Objects.isNull(writerId)) {
            throw new UnAuthorizedException("작성자에 값이 입력되질 않았어요 :(");
        }

        if (writerId.isEmpty()) {
            throw new UnAuthorizedException("작성자에 값이 입력되질 않았어요 :(");
        }

        if (id == 0L) {
            throw new IllegalArgumentException("유효하지 않는 아이디에요 :( [ 입력 값 : " + id + "]");
        }


        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public static Question of(String writerId, String title, String contents) {
        long id = SimpleIdGenerator.getAndIncrement(Question.class);
        return new Question(id, writerId, title, contents, LocalDateTime.now());
    }

    public static Question of(long id, String writerId, String title, String contents, LocalDateTime createdDate, DeleteStatus deleted) {

        Question question = new Question(id, writerId, title, contents, createdDate);

        if (Objects.nonNull(deleted)) {
            question.deleted = deleted;
        }
        return question;
    }

    public static Question of(long id, String writerId, String title, String contents, LocalDateTime createdDate) {
        return new Question(id, writerId, title, contents, createdDate);
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

    public String getWriter() {
        return writerId;
    }

    public boolean isOwner(User loginUser) {

        if (Objects.isNull(loginUser)) {
            throw new IllegalStateException("인가 되지 않은 사용자 에요 :(");
        }

        return loginUser.isUser(writerId);
    }

    public DeleteHistories remove(User requestUser, Answers answers) {
        QuestionRemoveValidator.validate(this, requestUser, answers);

        this.deleted = DeleteStatus.DELETED;
        this.updateAt = LocalDateTime.now();

        DeleteHistories deleteHistories = DeleteHistories.create();
        deleteHistories.add(DeleteHistory.of(ContentType.QUESTION, this.id, this.writerId));

        if (hasAnswer(answers)) {
            deleteHistories.concat(answers.removeAll());
        }
        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted.isDeleted();
    }

    public boolean hasAnswer(Answers answers) {

        if (Objects.nonNull(answers)) {
            return answers.hasAnswers();
        }

        return false;
    }

    public boolean hasAnotherOwner(Answers answers) {
        return answers.hasAnotherOwner(this.writerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && Objects.equals(writerId, question.writerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writerId);
    }

    private static class QuestionRemoveValidator {

        public static void validate(Question question, User requestUser, Answers answers) {
            validateAlreadyDeleted(question);
            validateAuthorization(question, requestUser);
            validateAboutAnswer(question, answers);
        }

        private static void validateAlreadyDeleted(Question question) {
            if (question.isDeleted()) {
                throw new CannotDeleteException("이미 삭제된 질문이에요 :(");
            }
        }

        private static void validateAuthorization(Question question, User requestUser) throws CannotDeleteException {
            if (!question.isOwner(requestUser)) {
                throw new CannotDeleteException("글 작성자만 삭제 가능해요 :( (요청 사용자 : " + requestUser.getUserId() + ")");
            }
        }

        private static void validateAboutAnswer(Question question, Answers answers) {

            if (!question.hasAnswer(answers)) {
                return;
            }

            if (question.hasAnotherOwner(answers)) {
                throw new CannotDeleteException("다른분이 작성한 답변글이 존재해서 삭제 불가능 해요 :(");
            }
        }

    }
}
