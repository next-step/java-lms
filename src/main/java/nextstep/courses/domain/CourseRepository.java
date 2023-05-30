package nextstep.courses.domain;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);

    int update(Course course);

    int delete(Course course);
}
