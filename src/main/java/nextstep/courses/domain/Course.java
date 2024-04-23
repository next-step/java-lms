package nextstep.courses.domain;

import nextstep.sessions.domain.Sessions;

import java.time.LocalDateTime;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(Long id, String title, Long creatorId, Sessions sessions) {
        this(id, title, creatorId, LocalDateTime.now(), null, sessions);
    }

    public Course(Long id, String title, Long creatorId) {
        this(id, title, creatorId, LocalDateTime.now(), null, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, creatorId, createdAt, updatedAt, null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt, Sessions sessions) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessions = sessions;
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

    public Long getId() {
        return id;
    }

    public int getSessionsSize() {
        return sessions.size();
    }
}
