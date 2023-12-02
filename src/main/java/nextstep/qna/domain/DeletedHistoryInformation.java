package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeletedHistoryInformation {

    private final ContentType contentType;

    private final Long contentId;

    private final NsUser deletedBy;

    private final LocalDateTime createdDate;

    public DeletedHistoryInformation(ContentType contentType, Long contentId, NsUser deletedBy) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = LocalDateTime.now();
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Long getContentId() {
        return contentId;
    }

    public NsUser getDeletedBy() {
        return deletedBy;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletedHistoryInformation that = (DeletedHistoryInformation) o;
        return contentType == that.contentType
                && Objects.equals(contentId, that.contentId)
                && Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentType, contentId, deletedBy, createdDate);
    }
}
