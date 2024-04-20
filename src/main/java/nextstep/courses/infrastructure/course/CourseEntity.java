package nextstep.courses.infrastructure.course;

import java.time.LocalDateTime;

import nextstep.courses.domain.course.Course;

public class CourseEntity {

    private final Long id;
    private final String title;
    private final Long creatorId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CourseEntity(
            final Long id,
            final String title,
            final Long creatorId,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public Long creatorId() {
        return creatorId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public Course toDomain() {
        return new Course(this.id, this.title, this.creatorId, this.createdAt, this.updatedAt);
    }

    public static CourseEntity fromDomain(final Course course) {
        return new CourseEntity(
                course.id(),
                course.title(),
                course.creatorId(),
                course.createdAt(),
                course.updatedAt()
        );
    }
}
