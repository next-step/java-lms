package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeletedHistory {

    private final DeletedHistoryInformation information;

    public DeletedHistory(ContentType contentType, Long contentId, NsUser deletedBy) {
        this.information = new DeletedHistoryInformation(contentType, contentId, deletedBy);
    }

    public ContentType getContentType() {
        return information.getContentType();
    }


    public Long getContentId() {
        return information.getContentId();
    }


    public NsUser getDeletedBy() {
        return information.getDeletedBy();
    }

    public LocalDateTime getCreatedDate() {
        return information.getCreatedDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletedHistory history = (DeletedHistory) o;
        return Objects.equals(information, history.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(information);
    }
}
