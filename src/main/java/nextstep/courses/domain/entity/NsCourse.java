package nextstep.courses.domain.entity;

import java.time.LocalDateTime;

public class NsCourse {

    public static final NsCourse DEFAULT_COURSE = new NsCourse("TDD, 클린 코드 with Java", 1L);

    private long id;

    private String title;

    private long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public NsCourse() {}

    public NsCourse(String title, long creatorId) {
        this(title, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public NsCourse(String title,
                    long creatorId,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.id = 1;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
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
