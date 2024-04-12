package nextstep.courses.domain;

import nextstep.sessions.domain.Session;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private List<Session> sessions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, Collections.emptyList(), creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, Collections.emptyList(), creatorId, createdAt, updatedAt);
    }

    public Course(Long id, String title, List<Session> sessions, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.sessions = sessions;
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
