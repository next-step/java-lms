package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserCode;
import nextstep.utils.DomainId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class Answer {
    private Long answerId;
    @NotNull
    private NsUser writer;
    @NotNull
    private QuestionId questionId;
    @NotBlank
    private String contents;
    private boolean deleted = false;
    private final LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;

    public Answer() {
    }

    public Answer(Long answerId, NsUser writer, QuestionId questionId, String contents, boolean deleted, LocalDateTime updatedDate) {
        this.answerId = answerId;
        this.writer = writer;
        this.questionId = questionId;
        this.contents = contents;
        this.deleted = deleted;
        this.updatedDate = updatedDate;
    }

    public static Answer of(Long answerId, NsUser writer, Question question, String contents) {
        return new Answer(
                answerId,
                writer,
                new QuestionId(question.getId()),
                contents,
                false,
                LocalDateTime.now()
        );
    }

    public Long getAnswerId() {
        return answerId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(UserCode writer) {
        return this.writer.getUserCode().value().equals(writer.value());
    }

    public void relateToQuestion(Question question) {
        this.questionId = question.getQuestionId();
    }

    @Override
    public String toString() {
        return "Answer{" +
                "createdDate=" + createdDate +
                ", id=" + answerId +
                ", writer=" + writer +
                ", questionId=" + questionId +
                ", contents='" + contents + '\'' +
                ", deleted=" + deleted +
                ", updatedDate=" + updatedDate +
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
}
