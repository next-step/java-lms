package nextstep.courses.domain.cource;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);
}
