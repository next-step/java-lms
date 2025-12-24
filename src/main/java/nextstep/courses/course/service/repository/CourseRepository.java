package nextstep.courses.course.service.repository;

import java.util.Optional;
import nextstep.courses.course.domain.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository {
    int save(Course course);

    Optional<Course> findById(Long id);
}
