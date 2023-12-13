package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);
}
