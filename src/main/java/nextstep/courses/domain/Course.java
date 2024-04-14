package nextstep.courses.domain;

import nextstep.session.domain.Session;
import nextstep.session.domain.Sessions;

import java.time.LocalDateTime;

public class Course {
    private Long id;

    private CourseName courseName;

    private Long creatorId;

    private final Sessions sessions;

    private final Generation generation;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course(String title, Long creatorId, int generation) {
        this(0L, title, creatorId, generation, LocalDateTime.now(), null);
    }

    public Course(
            Long id, String title, Long creatorId, int generation,
            LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        this.id = id;
        this.courseName = new CourseName(title);
        this.creatorId = creatorId;
        this.sessions = new Sessions();
        this.generation = new Generation(generation);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.courseName.toString();
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public int getGeneration() {
        return this.generation.generationNumber();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + courseName.toString() + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
