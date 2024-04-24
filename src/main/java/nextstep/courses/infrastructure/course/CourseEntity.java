package nextstep.courses.infrastructure.course;

import java.time.LocalDateTime;

import nextstep.common.BaseDateTime;
import nextstep.courses.domain.course.Course;

public class CourseEntity extends BaseDateTime {

    private final Long id;
    private final String title;
    private final Long creatorId;

    public CourseEntity(final String title, final Long creatorId) {
        this(null, title, creatorId);
    }

    public CourseEntity(final Long id, final String title, final Long creatorId) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
    }

    public CourseEntity(
            final Long id,
            final String title,
            final Long creatorId,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        super(createdAt, updatedAt);

        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
    }

    public Long id() {
        return this.id;
    }

    public String title() {
        return this.title;
    }

    public Long creatorId() {
        return this.creatorId;
    }

    public Course toDomain() {
        return new Course(this.id, this.title, this.creatorId, createdAt(), updatedAt());
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
