package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private boolean isFree;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startedAt = null;
        this.endedAt = null;
        this.isFree = true;
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

    public void patchTerms(LocalDateTime startedAt, LocalDateTime endedAt){
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }
    public List<LocalDateTime> terms() {
        return List.of(startedAt, endedAt);
    }

    public void patchIsFree(boolean input) {
        this.isFree = input;
    }

    public boolean isFree() {
        return isFree;
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
