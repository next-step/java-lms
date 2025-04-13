package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private int batchNumber;

    private Sessions sessions = new Sessions();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId, int batchNumber) {
        this(0L, title, creatorId, batchNumber, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, int batchNumber, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.batchNumber = batchNumber;
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

    public int getBatchNumber() {
        return batchNumber;
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
            ", batchNumber=" + batchNumber +
            ", sessions=" + sessions +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
