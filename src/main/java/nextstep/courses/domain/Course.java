package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {

    private final Long id;

    private final List<Session> sessions;

    private final String title;

    private final Long creatorId;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public Course(String title, Long creatorId) {
        this(0L, new ArrayList<>(), title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this(id, new ArrayList<>(), title, creatorId, createdAt, updatedAt);
    }

    public Course(Long id, List<Session> sessions, String title, Long creatorId, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.sessions = sessions;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
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
        return id.equals(course.id);
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
