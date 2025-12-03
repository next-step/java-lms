package nextstep.courses.domain;

public interface CourseRepository {
    Long save(Course course);

    Course findById(Long id);
}
