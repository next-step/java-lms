package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Course {
    private final Long id;

    private final String title;

    private final Generation generation;

    private final Sessions sessions;

    private final Long creatorId;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course(Integer generation, Sessions sessions) {
        this(0L, "", generation, sessions, 0L);
    }

    private Course(Long id, String title, Integer generation, Sessions sessions, Long creatorId) {
        this.id = id;
        this.title = title;
        this.generation = new Generation(generation);
        this.sessions = sessions;
        this.creatorId = creatorId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public Generation getGeneration() {
        return generation;
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
