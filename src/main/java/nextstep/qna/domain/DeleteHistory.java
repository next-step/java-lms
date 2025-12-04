package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeleteHistory {
    private final Long id;
    private final ContentType contentType;
    private final NsUser deletedBy;
    private final LocalDateTime createdAt;


    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy) {
        this(contentType, contentId, deletedBy, LocalDateTime.now());
    }


    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdAt) {
        this.id = contentId;
        this.contentType = contentType;
        this.deletedBy = deletedBy;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return contentType == that.contentType && Objects.equals(id, that.id) && Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentType, id, deletedBy, createdAt);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", contentType=" + contentType + ", contentId=" + id + ", deletedBy="
                + deletedBy + ", createdDate=" + createdAt + "]";
    }
}
