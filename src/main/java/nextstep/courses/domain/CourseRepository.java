package nextstep.courses.domain;

import java.util.Optional;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);

    int saveUser(CourseUser courseUser);

    CourseUser findByUserId(Long userId);
}
