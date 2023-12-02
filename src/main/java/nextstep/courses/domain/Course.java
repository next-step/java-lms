package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private List<Group> groups;

    private Sessions sessions;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.groups = Collections.emptyList();
        this.sessions = Sessions.initialize();
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addSession(Session session) {
        session.toCourse(this);
        this.sessions = sessions.addSession(session);
    }

    public String getTitle() {
        return title;
    }

    public Sessions getSessions() {
        return sessions;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
