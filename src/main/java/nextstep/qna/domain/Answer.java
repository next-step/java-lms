package nextstep.qna.domain;

import nextstep.users.domain.UserCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Answer {
    private Long answerId;
    @NotNull
    private UserCode writer;
    @NotNull
    private QuestionId questionId;
    @NotBlank
    private String contents;
    private boolean deleted = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public Answer() {
    }

    public Answer(Long answerId, UserCode writer, QuestionId questionId, String contents, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.answerId = answerId;
        this.writer = writer;
        this.questionId = questionId;
        this.contents = contents;
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Answer of(Long answerId, UserCode writer, Question question, String contents) {
        return new Answer(
                answerId,
                writer,
                new QuestionId(question.getId()),
                contents,
                false,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public Long getAnswerId() {
        return answerId;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public boolean isOwner(UserCode writer) {
        return this.writer.value().equals(writer.value());
    }

    public void relateToQuestion(Question question) {
        this.questionId = question.getQuestionId();
    }

    @Override
    public String toString() {
        return "Answer{" +
                "createdDate=" + createdAt +
                ", id=" + answerId +
                ", writer=" + writer +
                ", questionId=" + questionId +
                ", contents='" + contents + '\'' +
                ", deleted=" + deleted +
                ", updatedDate=" + updatedAt +
                '}';
    }

    public DeleteHistory toDeleteHistory() {
        return DeleteHistory.of(ContentType.ANSWER, this.getAnswerId(), this.writer, LocalDateTime.now());
    }

    public void doDelete() {
        this.deleted = true;
    }

    public String getContent() {
        return this.contents;
    }

    public QuestionId getQuestionId() {
        return this.questionId;
    }

    public boolean isRelated(Question question) {
        return this.questionId == question.getQuestionId();
    }

    public UserCode getWriter() {
        return this.writer;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
