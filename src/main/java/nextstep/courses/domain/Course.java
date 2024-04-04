package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private Sessions sessions;

    public Course() {
    }

    public Course(Long id) {
        this.id = id;
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, new Sessions(new ArrayList<>()), LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, title, creatorId, new Sessions(new ArrayList<>()), createdAt, updatedAt);
    }


    public Course(Long id, String title, Long creatorId, Sessions sessions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
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

    public Session getSession(Object sessionIdx) {
        return null;
    }
}
