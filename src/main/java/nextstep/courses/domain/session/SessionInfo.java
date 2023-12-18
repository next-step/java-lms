package nextstep.courses.domain.session;

public class SessionInfo {
    private long id;
    private String title;
    private long courseId;
    private SessionType sessionType;

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
}
