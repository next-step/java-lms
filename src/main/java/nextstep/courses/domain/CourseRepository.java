package nextstep.courses.domain;

import java.util.Optional;

public interface CourseRepository {
    long save(Course course);

    Optional<Course> findById(Long id);
}
