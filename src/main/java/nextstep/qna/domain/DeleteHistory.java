package nextstep.qna.domain;

import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeleteHistory {
    private final long id;

    private final ContentType contentType;

    private final long contentId;

    private final NsUser deletedBy;

    private final LocalDateTime createdDate;

    public DeleteHistory(long id, ContentType contentType, long contentId, NsUser deletedBy, LocalDateTime createdDate) {
        this.id = id;
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
    }

    public static DeleteHistory of(ContentType contentType, long contentId, NsUser deletedBy) {
        long id = SimpleIdGenerator.getAndIncrement(DeleteHistory.class);
        return new DeleteHistory(id, contentType, contentId, deletedBy, LocalDateTime.now());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return id == that.id && contentId == that.contentId && contentType == that.contentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contentId);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", contentType=" + contentType + ", contentId=" + contentId + ", deletedBy="
                + deletedBy + ", createdDate=" + createdDate + "]";
    }
}
