package nextstep.courses.domain.course;

import java.util.Optional;

public interface CourseRepository {

    int save(final Course course);

    Optional<Course> findById(final Long id);
}
