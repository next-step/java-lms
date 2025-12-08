package nextstep.courses.infrastructure.entity;

import java.time.LocalDateTime;

public class CourseEntity {
    private final Long id;
    private final String title;
    private final Long creatorId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CourseEntity(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}