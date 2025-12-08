package nextstep.courses.infrastructure.mapper;

import nextstep.courses.domain.course.Course;
import nextstep.courses.infrastructure.entity.CourseEntity;

public class CourseMapper {

    private CourseMapper() {
    }

    public static CourseEntity toEntity(Course course) {
        return new CourseEntity(
            course.getId(),
            course.getTitle(),
            course.getCreatorId(),
            course.getCreatedAt(),
            course.getUpdatedAt()
        );
    }

    public static Course toDomain(CourseEntity entity) {
        return new Course(
            entity.getId(),
            entity.getTitle(),
            entity.getCreatorId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}