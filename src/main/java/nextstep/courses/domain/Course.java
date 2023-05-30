package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title)
            && Objects.equals(creatorId, course.creatorId) && Objects.equals(
            createdAt, course.createdAt) && Objects.equals(updatedAt, course.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creatorId, createdAt, updatedAt);
    }

    public Long id() {
        return id;
    }
}
