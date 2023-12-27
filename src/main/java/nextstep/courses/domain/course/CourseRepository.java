package nextstep.courses.domain.course;

public interface CourseRepository {
    Course save(Course course);

    Course findById(Long id);
}
