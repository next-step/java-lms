package nextstep.courses.domain;

import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);

    Optional<Course> findById(Long id);
}
