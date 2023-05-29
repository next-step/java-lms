package nextstep.courses.domain;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.Sessions;

import java.time.LocalDateTime;

public class Course {
    private Long id;

    private Sessions sessions = Sessions.create();

    private int generation;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Course(Long id, int generation, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.generation = generation;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public static Course of(String title, int generation, Long creatorId) {
        return new Course(0L, generation, title, creatorId, LocalDateTime.now(), null);
    }

    public static Course of(Long id, int generation, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Course(id, generation, title, creatorId, createdAt, updatedAt);
    }

    public void addSession(Session session) {
        session.toCourse(this);
        sessions.add(session);
    }

    public Sessions getSessions() {
        return sessions;
    }

    public String getTitle() {
        return title;
    }

    public int getGeneration() {
        return generation;
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
