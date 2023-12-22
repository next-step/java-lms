package nextstep.courses.domain.session;

import java.util.Objects;

public class SessionInfo {
    private final long id;
    private final String title;
    private final long courseId;
    private final SessionType sessionType;

    public SessionInfo(long id, String title, long courseId, SessionType sessionType) {
        this.id = id;
        this.title = title;
        this.courseId = courseId;
        this.sessionType = sessionType;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getCourseId() {
        return courseId;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionInfo that = (SessionInfo) o;
        return id == that.id && courseId == that.courseId && Objects.equals(title, that.title) && sessionType == that.sessionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, courseId, sessionType);
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", courseId=" + courseId +
                ", sessionType=" + sessionType +
                '}';
    }
}
