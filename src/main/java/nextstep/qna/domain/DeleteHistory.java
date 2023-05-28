package nextstep.qna.domain;

import nextstep.users.domain.UserCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeleteHistory {
    @NotNull
    private Long deleteHistoryId;
    @NotNull
    private ContentType contentType;
    @NotNull
    private Long contentId;
    @NotNull
    private UserCode deletedBy;
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public DeleteHistory(Long deleteHistoryId, ContentType contentType, Long contentId, UserCode deletedBy, LocalDateTime createdAt) {
        this.deleteHistoryId = deleteHistoryId;
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdAt = createdAt;
    }

    public static DeleteHistory of(ContentType contentType, Long contentId, UserCode deletedBy, LocalDateTime dateTime) {
        return new DeleteHistory(null, contentType, contentId, deletedBy, dateTime);
    }

    public static List<DeleteHistory> deleteHistoryHelper(List<Question> questions, List<Answer> answers) {
        List<DeleteHistory> questionDeleteHistories = questions.stream()
                .map(Question::toDeleteHistory)
                .collect(Collectors.toList());
        List<DeleteHistory> answerDeleteHistories = answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.toList());
        answerDeleteHistories.addAll(questionDeleteHistories);
        return answerDeleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory other = (DeleteHistory) o;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistoryId);
    }

    @Override
    public String toString() {
        return "DeleteHistory{" +
                "deleteHistoryId=" + deleteHistoryId +
                ", contentType=" + contentType +
                ", contentId=" + contentId +
                ", deletedBy=" + deletedBy +
                ", createdAt=" + createdAt +
                '}';
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public long getContentId() {
        return this.contentId;
    }

    public Long getDeleteHistoryId() {
        return deleteHistoryId;
    }

    public UserCode getDeletedBy() {
        return deletedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
