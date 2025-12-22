package nextstep.courses.domain.course;

public interface CourseRepository {
    Long save(Course course);

    Course findById(Long id);
}
