package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.domain.BaseEntity;

public class Course extends BaseEntity {
    private String title;
    private Long creatorId;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(null, title, creatorId);
    }

    public Course(Long id, String title, Long creatorId) {
        super(id);
        this.title = title;
        this.creatorId = creatorId;
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + getCreatedAt() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
