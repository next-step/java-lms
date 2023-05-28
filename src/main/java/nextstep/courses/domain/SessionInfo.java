package nextstep.courses.domain;

import java.util.Objects;

public class SessionInfo {

    private final Long courseId;
    private final Long ownerId;
    private final String title;
    private final String coverImageInfo;
    private final SessionType sessionType;

    public SessionInfo(Long courseId, Long ownerId, String title, String coverImageInfo, SessionType sessionType) {
        this.courseId = courseId;
        this.ownerId = ownerId;
        this.title = title;
        this.coverImageInfo = coverImageInfo;
        this.sessionType = sessionType;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverImageInfo() {
        return coverImageInfo;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInfo that = (SessionInfo) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(ownerId, that.ownerId) && Objects.equals(title, that.title) && Objects.equals(coverImageInfo, that.coverImageInfo) && sessionType == that.sessionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, ownerId, title, coverImageInfo, sessionType);
    }
}
