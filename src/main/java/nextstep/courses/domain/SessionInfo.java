package nextstep.courses.domain;

public class SessionInfo {
    private final String title;
    private final Long creatorId;
    private final Course course;

    public SessionInfo(String title, Long creatorId, Course course) {
        this.title = title;
        this.creatorId = creatorId;
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Long getCourseId() {
        return course.getId();
    }

    @Override
    public String toString() {
        return "SessionInfo{" +
                "title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", course=" + course +
                '}';
    }
}
