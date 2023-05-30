package nextstep.lms.repository;

import nextstep.lms.domain.Course;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);
}
