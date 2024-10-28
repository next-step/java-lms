package nextstep.courses.domain;

import java.util.List;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);

    List<Course> findAll();
}
