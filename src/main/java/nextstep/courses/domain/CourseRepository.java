package nextstep.courses.domain;

public interface CourseRepository {

    long save(Course course);

    Course findById(Long id);
}
