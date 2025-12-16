package nextstep.courses.course.service.repository;

import nextstep.courses.course.domain.Course;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);
}
