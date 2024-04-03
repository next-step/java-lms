package nextstep.qna.domain.deleteHistory;

import nextstep.qna.domain.BaseTime;
import nextstep.qna.domain.deleteHistory.type.ContentType;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class DeleteHistory extends BaseTime {
    private Long id;

    private ContentType contentType;

    private Long contentId;

    private NsUser deletedBy;

    public DeleteHistory() {
    }

    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
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
                + deletedBy + ", createdDate=" + super.createdDate + "]";
    }
}
