package nextstep.qna.domain;

import static java.time.LocalDateTime.now;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeleteHistory {

    private Long id;

    private ContentType contentType;

    private Long contentId;

    private NsUser deletedBy;

    private LocalDateTime createdDate;

    public DeleteHistory(ContentType contentType,
                         Long contentId,
                         NsUser deletedBy) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = now();
    }

    public DeleteHistory(ContentType contentType,
                         Answer answer) {
        this.contentType = contentType;
        this.contentId = answer.getId();
        this.deletedBy =  answer.getWriter();
        this.createdDate = now();
    }

    public Long getId() {
        return id;
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
        return createdDate;
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
        return "DeleteHistory [id=" + getId() +
               ", contentType=" + getContentType() +
               ", contentId=" + getContentId() +
               ", deletedBy=" + getDeletedBy() +
               ", createdDate=" + getCreatedDate() +
               "]";
    }
}
