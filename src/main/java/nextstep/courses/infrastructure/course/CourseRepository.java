package nextstep.courses.infrastructure.course;

import java.util.Optional;

public interface CourseRepository {

    Long save(final CourseEntity courseEntity);

    Optional<CourseEntity> findById(final Long id);
}
