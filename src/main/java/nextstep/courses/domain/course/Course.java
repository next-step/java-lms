package nextstep.courses.domain.course;

import nextstep.courses.domain.session.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long generation;  // 기수

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private final List<Session> sessions  = new ArrayList<>();

    public static Course of(Long id, String title, Long generation, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Session> sessions) {
        return new Course(id, title, generation, creatorId, createdAt, updatedAt, sessions);
    }

    public Course(String title, Long creatorId) {
        this(0L, title, 0L, creatorId, LocalDateTime.now(), null, List.of());
    }

    public Course(Long id, String title, Long generation, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Session> sessions) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessions.addAll(sessions);
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

    public int getNumberOfSessions() {
        return this.sessions.size();
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
