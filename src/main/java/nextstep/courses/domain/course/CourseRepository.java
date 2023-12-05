package nextstep.courses.domain.course;

public interface CourseRepository {
    int save(Course course);

    Course findById(Long id);
}
