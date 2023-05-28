package nextstep.qna.domain;

import java.util.Objects;
import nextstep.common.entity.BaseEntity;
import nextstep.users.domain.NsUser;

public class DeleteHistory extends BaseEntity {

    private final ContentMeta contentMeta;

    private final NsUser deletedBy;

    public DeleteHistory() {
        this(null, null);
    }

    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy) {
        this(new ContentMeta(contentType, contentId), deletedBy);
    }

    public DeleteHistory(ContentMeta contentMeta, NsUser deletedBy) {
        this.contentMeta = contentMeta;
        this.deletedBy = deletedBy;
    }

    public boolean isEmpty() {
        return this.equals(new DeleteHistory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(contentMeta, that.contentMeta) && Objects.equals(
                deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentMeta, deletedBy);
    }
}
