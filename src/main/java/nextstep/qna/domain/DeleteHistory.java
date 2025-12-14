package nextstep.qna.domain;

import static java.util.Objects.*;

import nextstep.common.domain.BaseEntity;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeleteHistory extends BaseEntity {
    private ContentType contentType;
    private Long contentId;
    private Long deletedId;

    public DeleteHistory(ContentType contentType, Long contentId, Long deletedId, LocalDateTime createdDate) {
        this(0L, contentType, contentId, deletedId, createdDate, null);
    }

    public DeleteHistory(Long id, ContentType contentType, Long contentId, Long deletedId, LocalDateTime createdDate, LocalDateTime updateDate) {
        super(id, createdDate, updateDate);
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedId = deletedId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteHistory that = (DeleteHistory) o;
        return super.equals(o)
                && contentType == that.contentType
                && Objects.equals(contentId, that.contentId)
                && Objects.equals(deletedId, that.deletedId);
    }

    @Override
    public int hashCode() {
        return hash(super.hashCode(), contentType, contentId, deletedId);
    }

    @Override
    public String toString() {
        return "DeleteHistory{" +
                "id=" + super.toString() +
                ", contentType=" + contentType +
                ", contentId=" + contentId +
                ", deletedId=" + deletedId +
                '}';
    }
}
