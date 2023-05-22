package nextstep.qna.domain.vo;

import nextstep.qna.domain.enums.ContentType;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class DeleteHistoryDetail {
    private Long contentId;

    private ContentType contentType;

    private NsUser deletedBy;

    public static DeleteHistoryDetail of(Long contentId, ContentType contentType, NsUser deletedBy) {
        return new DeleteHistoryDetail(contentId, contentType, deletedBy);
    }

    private DeleteHistoryDetail(Long contentId, ContentType contentType, NsUser deletedBy) {
        this.contentId = contentId;
        this.contentType = contentType;
        this.deletedBy = deletedBy;
    }

    public Long getContentId() {
        return contentId;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public NsUser getDeletedBy() {
        return deletedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistoryDetail that = (DeleteHistoryDetail) o;
        return Objects.equals(contentId, that.contentId) && contentType == that.contentType && Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId, contentType, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistoryDetail{" +
                "contentId=" + contentId +
                ", contentType=" + contentType +
                ", deletedBy=" + deletedBy +
                '}';
    }
}
