package nextstep.lms.domain;

import java.util.Optional;

public interface CourseRepository {
    int save(Course course);

    Optional<Course> findById(Long id);
}
