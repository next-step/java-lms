package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.users.domain.NsUser;

public class Course extends AuditInfo{
    private Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions;

    public Course() {
        super(LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Course(Long id, String title, Long creatorId, Sessions sessions) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createAt, LocalDateTime updateAt) {
        super(createAt, updateAt);
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = new Sessions();
    }


    public void enroll(NsUser user, Long sessionId) {
        sessions.enroll(user, sessionId);
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
