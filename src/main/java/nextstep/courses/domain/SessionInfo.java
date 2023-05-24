package nextstep.courses.domain;

import java.util.Objects;

public class SessionInfo {

    private final Long ownerId;
    private final String title;
    private final String coverImageInfo;
    private final SessionType sessionType;

    public SessionInfo(Long ownerId, String title, String coverImageInfo, SessionType sessionType) {
        this.ownerId = ownerId;
        this.title = title;
        this.coverImageInfo = coverImageInfo;
        this.sessionType = sessionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInfo that = (SessionInfo) o;
        return Objects.equals(ownerId, that.ownerId) && Objects.equals(title, that.title) && Objects.equals(coverImageInfo, that.coverImageInfo) && sessionType == that.sessionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, title, coverImageInfo, sessionType);
    }
}
