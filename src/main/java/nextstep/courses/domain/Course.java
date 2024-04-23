package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Sessions sessions;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null, List.of());
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Session> sessions) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessions = new Sessions(sessions);
    }

    public static Course of(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Session> sessions) {
        return new Course(id, title, creatorId, createdAt, updatedAt, sessions);
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

    public int getNumberOfSessions() {
        return this.sessions.size();
    }
}
