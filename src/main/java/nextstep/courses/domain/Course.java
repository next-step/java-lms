package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.users.domain.NsUser;

public class Course extends AuditInfo{
    private final Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions;

    public Course(Long id, String title, Long creatorId, Sessions sessions, LocalDateTime createAt, LocalDateTime updateAt) {
        super(createAt, updateAt);
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
    }

    public Course(Course course){
        this(course.id, course.title, course.creatorId, course.sessions, course.createdAt, course.updatedAt);
    }

    public void addSessions(Sessions sessions){
        this.sessions.addAll(sessions);
    }

    public Session enroll(NsUser user, Long sessionId) {
        return sessions.enroll(user, sessionId);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
