package nextstep.qna.domain;

import nextstep.qna.domain.enums.ContentType;
import nextstep.qna.domain.vo.DeleteHistoryDetail;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeleteHistory {
    private Long id;

    private DeleteHistoryDetail detail;

    private LocalDateTime createdDate;

    public static DeleteHistory of (ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdDate) {
        return new DeleteHistory(contentType, contentId, deletedBy, createdDate);
    }

    private DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdDate) {
        this.detail = DeleteHistoryDetail.of(contentId, contentType, deletedBy);
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, detail);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", contentType=" + detail.getContentType() + ", contentId=" + detail.getContentId() + ", deletedBy="
                + detail.getDeletedBy() + ", createdDate=" + createdDate + "]";
    }
}
