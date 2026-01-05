package nextstep.qna.domain;

import java.util.Objects;

public class ContentStatus {
    private final ContentTimestamp timestamp;
    private boolean deleted;

    public ContentStatus() {
        this.deleted = false;
        this.timestamp = new ContentTimestamp();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete() {
        deleted = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContentStatus that = (ContentStatus) o;
        return deleted == that.deleted && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleted, timestamp);
    }
}
