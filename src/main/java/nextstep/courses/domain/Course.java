package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Course {
    private Long id;
    private String title;
    private Long creatorId;
    private Integer period;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Sessions sessions;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(String title, Long creatorId, Integer period) {
        this(0L, title, creatorId, period, LocalDateTime.now(), null, null);
    }

    public Course(String title, Long creatorId, Integer period, Sessions sessions) {
        this(0L, title, creatorId, period, LocalDateTime.now(), null, sessions);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Course(Long id, String title, Long creatorId, Integer period,
                  LocalDateTime createdAt, LocalDateTime updatedAt, Sessions sessions) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.period = period;
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

    public Integer getPeriod() {
        return period;
    }

    public Sessions getSessions() {
        return sessions;
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
