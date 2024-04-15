package nextstep.courses.domain;

import java.util.Objects;

public class SessionInfo {
    private final String title;

    private final Long creatorId;

    public SessionInfo(String title, Long creatorId) {
        this.title = title;
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInfo that = (SessionInfo) o;
        return Objects.equals(title, that.title) && Objects.equals(creatorId, that.creatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creatorId);
    }
}
