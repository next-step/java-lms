package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.users.domain.NsUser;

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

    public static DeleteHistory createDeleteHistoryByQuestion(Long contentId, NsUser deletedBy,
                                                              LocalDateTime createdDate) {
        DeleteHistory deleteHistory = new DeleteHistory();
        deleteHistory.contentType = ContentType.QUESTION;
        deleteHistory.contentId = contentId;
        deleteHistory.deletedBy = deletedBy;
        deleteHistory.createdDate = createdDate;
        return deleteHistory;
    }

    public static DeleteHistory createDeleteHistoryByAnswer(Long contentId, NsUser deletedBy,
                                                            LocalDateTime createdDate) {
        DeleteHistory deleteHistory = new DeleteHistory();
        deleteHistory.contentType = ContentType.ANSWER;
        deleteHistory.contentId = contentId;
        deleteHistory.deletedBy = deletedBy;
        deleteHistory.createdDate = createdDate;
        return deleteHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
}
