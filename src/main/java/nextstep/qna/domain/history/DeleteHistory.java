package nextstep.qna.domain.history;

import java.time.LocalDateTime;
import java.util.Objects;

import nextstep.users.domain.NsUser;

public class DeleteHistory {

    private final Long id;
    private final ContentType contentType;
    private final Long contentId;
    private final NsUser deletedBy;
    private final LocalDateTime createdDate;

    public DeleteHistory(
            final ContentType contentType,
            final Long contentId,
            final NsUser deletedBy,
            final LocalDateTime createdDate
    ) {
        this(null, contentType, contentId, deletedBy, createdDate);
    }

    public DeleteHistory(
            final Long id,
            final ContentType contentType,
            final Long contentId,
            final NsUser deletedBy,
            final LocalDateTime createdDate
    ) {
        this.id = id;
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(final Object otherDeleteHistory) {
        if (this == otherDeleteHistory) {
            return true;
        }

        if (otherDeleteHistory == null || getClass() != otherDeleteHistory.getClass()) {
            return false;
        }

        final DeleteHistory that = (DeleteHistory)otherDeleteHistory;

        return Objects.equals(this.id, that.id) &&
                this.contentType == that.contentType &&
                Objects.equals(this.contentId, that.contentId) &&
                Objects.equals(this.deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.contentType, this.contentId, this.deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + this.id + ", contentType=" + this.contentType + ", contentId=" + this.contentId
                + ", deletedBy=" + this.deletedBy + ", createdDate=" + this.createdDate + "]";
    }
}
