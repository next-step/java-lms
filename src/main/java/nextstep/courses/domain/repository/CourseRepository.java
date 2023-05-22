package nextstep.courses.domain.repository;

import nextstep.courses.domain.Course;

public interface CourseRepository {
    long save(Course course);
    Course findById(Long id);
    int update(Course course);
    int delete(Long id);
}
