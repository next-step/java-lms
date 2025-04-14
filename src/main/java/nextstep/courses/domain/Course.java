package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.Sessions;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private int generation;

    private Sessions sessions = new Sessions();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId, int generation) {
        this(0L, title, creatorId, generation, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, int generation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.generation = generation;
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

    public int getGeneration() {
        return generation;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public Sessions getSessions() {
        return sessions;
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
            ", batchNumber=" + generation +
            ", sessions=" + sessions +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
