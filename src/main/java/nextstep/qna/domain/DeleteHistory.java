package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeleteHistory {
    private Long id;

    private ContentType contentType;

    private Long contentId;

    private NsUser deletedBy;

    private LocalDateTime createdDate = LocalDateTime.now();

    public DeleteHistory() {
    }

    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
    }

    public static DeleteHistory of(ContentType contentType, Long id, NsUser writer, LocalDateTime dateTime) {
        return new DeleteHistory(contentType, id, writer, dateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) &&
                contentType == that.contentType &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contentId, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", contentType=" + contentType + ", contentId=" + contentId + ", deletedBy="
                + deletedBy + ", createdDate=" + createdDate + "]";
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
}
