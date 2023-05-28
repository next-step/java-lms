package nextstep.qna.domain;

import nextstep.qna.exception.QuestionDeleteAnswerExistedException;
import nextstep.qna.exception.QuestionDeleteUnauthorizedException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Question {

    private QuestionId questionId;
    private String title;
    private String contents;
    private UserCode writer;
    private boolean deleted = false;
    private List<Answer> answers = new ArrayList<>();
    private LocalDateTime updatedDate;
    @NotNull
    private LocalDateTime createdDate;

    public Question(QuestionId questionId, String title, String contents, UserCode writer, boolean deleted, List<Answer> answers, LocalDateTime updatedDate, LocalDateTime createdDate) {
        this.questionId = questionId;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.deleted = deleted;
        this.answers = answers;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
    }

    public Question(QuestionId questionId, String title, String contents, UserCode writer, boolean deleted, LocalDateTime updatedDate) {
        this.questionId = questionId;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.deleted = deleted;
        this.updatedDate = updatedDate;
        this.createdDate = LocalDateTime.now();
    }

    public static Question of(Long questionId, UserCode writer, String title, String contents) {
        return new Question(
                new QuestionId(questionId),
                title,
                contents,
                writer,
                false,
                LocalDateTime.now()
        );
    }

    public static Question of(QuestionId questionId, Question question) {
        return new Question(
                questionId,
                question.title,
                question.contents,
                question.writer,
                question.deleted,
                new ArrayList<>(question.answers), // Shallow copy of the answers list
                question.updatedDate,
                question.createdDate
        );
    }
    public Long getId() {
        return this.questionId.value();
    }

    public QuestionId getQuestionId() {
        return questionId;
    }

    public void addAnswer(Answer answer) {
        answer.relateToQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete(UserCode userCode) {
        validateDelete(userCode);
        deleteQuestionAndAnswers();
        return makeDeleteHistories();
    }

    private void deleteQuestionAndAnswers() {
        this.doDelete();
        answers.forEach(Answer::doDelete);
    }

    private void doDelete() {
        this.deleted = true;
    }

    private List<DeleteHistory> makeDeleteHistories() {
        List<DeleteHistory> deleteHistories = answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.toList());
        deleteHistories.add(this.toDeleteHistory());
        return deleteHistories;
    }

    private void validateDelete(UserCode userCode) {
        validateQuestionOwner(userCode);
        validateAnswers(userCode);
    }

    private void validateAnswers(UserCode userCode) {
        answers.stream()
                .filter(answer -> !answer.isOwner(userCode))
                .findAny().ifPresent(answer -> {
                    throw new QuestionDeleteAnswerExistedException();
                });
    }

    private void validateQuestionOwner(UserCode userCode) {
        if (!writer.equals(userCode)) {
            throw new QuestionDeleteUnauthorizedException();
        }
    }

    public DeleteHistory toDeleteHistory() {
        return DeleteHistory.of(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now());
    }

    public boolean isRelated(Answer answer) {
        return this.answers.contains(answer);
    }

    public String getTitle() {
        return this.title;
    }

    public String getContents() {
        return this.contents;
    }

    public LocalDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public UserCode getWriter() {
        return this.writer;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public boolean getDeleted() {
        return this.deleted;
    }
}
