package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.Objects;

public class DeleteHistory extends BaseEntity {
    private ContentType contentType;
    private Long contentId;
    private NsUser deletedBy;

    public DeleteHistory(Long id, ContentType contentType, Long contentId, NsUser deletedBy) {
        super(id);
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
    }
    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy) {
        this(null, contentType, contentId, deletedBy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(getId(), that.getId()) &&
                contentType == that.contentType &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), contentType, contentId, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + getId() + ", contentType=" + contentType + ", contentId=" + contentId
                + ", deletedBy=" + deletedBy + ", createdDate=" + getCreatedDate() + "]";
    }
}
