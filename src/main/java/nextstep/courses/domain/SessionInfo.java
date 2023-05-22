package nextstep.courses.domain;

import java.util.Objects;

public class SessionInfo {
    private final String title;

    private final Long creatorId;

    private final String coverImage;

    public SessionInfo(String title, Long creatorId, String coverImage) {
        this.title = title;
        this.creatorId = creatorId;
        this.coverImage = coverImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInfo that = (SessionInfo) o;
        return Objects.equals(title, that.title) && Objects.equals(creatorId, that.creatorId) && Objects.equals(coverImage, that.coverImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creatorId, coverImage);
    }
}
