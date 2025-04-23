package nextstep.courses.factory;

import nextstep.courses.domain.Course;
import nextstep.courses.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseFactory {

    public CourseEntity createCourseEntity(Course course) {
        return CourseEntity.builder()
            .id(course.id())
            .title(course.getTitle())
            .creatorId(course.getCreatorId())
            .createdAt(course.getCreatedAt())
            .updatedAt(course.getUpdatedAt())
            .deleted(course.isDeleted())
            .build();
    }
}
