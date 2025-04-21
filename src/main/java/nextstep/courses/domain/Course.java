package nextstep.courses.domain;

import lombok.Getter;
import nextstep.common.domian.BaseDomain;
import nextstep.courses.domain.session.Sessions;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Course extends BaseDomain {

    private final String title;

    private final Long creatorId;

    private final Sessions sessions;

    public Course(String title, Long creatorId) {
        this(null, title, creatorId, new Sessions(), LocalDateTime.now(), null);
    }

    public Course(String id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, creatorId, new Sessions(), createdAt, updatedAt);
    }

    public Course(String id, String title, Long creatorId, Sessions sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, false, title, creatorId, sessions, createdAt, updatedAt);
    }

    public Course(String id, boolean deleted, String title, Long creatorId, Sessions sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, deleted, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
    }

    public void delete() {
        sessions.delete();
        this.deleted = true;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
            Objects.equals(title, course.title) &&
            Objects.equals(creatorId, course.creatorId) &&
            Objects.equals(createdAt, course.createdAt) &&
            Objects.equals(updatedAt, course.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creatorId, createdAt, updatedAt);
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
