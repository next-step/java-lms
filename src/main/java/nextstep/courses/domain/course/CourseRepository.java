package nextstep.courses.domain.course;

import nextstep.courses.domain.course.Course;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);
}
