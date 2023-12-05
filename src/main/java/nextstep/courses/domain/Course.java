package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private final Long id;

    private final String title;

    private final Long creatorId;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    private List<Session> sessions = new ArrayList<>();

    public List<Session> sessions() {
        return sessions;
    }

    public boolean contains(Session session) {
        return sessions.contains(session);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
