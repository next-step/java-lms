package nextstep.qna.infrastructure.entity;

import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.DeletedHistory;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeletedHistoryEntity {
    private Long id;

    private ContentType contentType;

    private Long contentId;

    private NsUser deletedBy;

    private LocalDateTime createdDate = LocalDateTime.now();

    public DeletedHistoryEntity() {
    }

    public DeletedHistoryEntity(ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
    }

    public static DeletedHistoryEntity toEntity(DeletedHistory history) {
        return new DeletedHistoryEntity(history.getContentType(),
                                        history.getContentId(),
                                        history.getDeletedBy(),
                                        history.getCreatedDate());
    }

    public DeletedHistory toModel() {
        return new DeletedHistory(this.contentType, this.contentId, this.deletedBy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletedHistoryEntity that = (DeletedHistoryEntity) o;
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
