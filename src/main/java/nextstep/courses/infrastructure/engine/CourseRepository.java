package nextstep.courses.infrastructure.engine;

import nextstep.courses.domain.Course;

import java.util.Optional;

public interface CourseRepository {
    int save(Course course);

    Optional<Course> findById(Long id);
}
