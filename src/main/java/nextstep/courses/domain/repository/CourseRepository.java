package nextstep.courses.domain.repository;

import nextstep.courses.domain.model.Course;

public interface CourseRepository {
    long save(Course course);

    Course findById(Long id);
}
