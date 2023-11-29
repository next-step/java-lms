package nextstep.courses.domain;

import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;

public class Course {
    private Long id;

    private Integer generation;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateCourse(id, title, creatorId, createdAt);

        this.id = id;
        this.generation = 1;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Course(final String title, final int generation, final long creatorId) {
        this(title, creatorId);

        validateGeneration(generation);
        this.generation = generation;
    }

    private void validateCourse(final Long id, final String title, final Long creatorId, final LocalDateTime createdAt) {
        Assert.notNull(id, "id must not be null");
        Assert.hasText(title, "title must not be empty");
        Assert.isTrue(creatorId > 0, "creatorId must be greater than 0");
        Assert.notNull(createdAt, "createdAt must not be null");
    }

    private void validateGeneration(final int generation) {
        Assert.isTrue(generation > 0, "generation must be greater than 0");
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Course course = (Course) o;
        return Objects.equals(generation, course.generation)
                && Objects.equals(getTitle(), course.getTitle())
                && Objects.equals(getCreatorId(), course.getCreatorId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(generation, getTitle(), getCreatorId());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", generation=" + generation +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
