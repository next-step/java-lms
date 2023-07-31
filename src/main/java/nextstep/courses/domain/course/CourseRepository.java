package nextstep.courses.domain.course;

import java.util.Optional;

public interface CourseRepository {

  Optional<Course> findById(Long id);

  Long save(Course course);
}
