package nextstep.courses.domain.course;

public interface CourseRepository {

    int save(final Course course);

    Course findById(final Long id);
}
