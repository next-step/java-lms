package nextstep.courses.sevice;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.dto.CreateCourseRequest;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void saveCourse(CreateCourseRequest request) {
        courseRepository.save(request.toEntity());
    }

    public Course findCourse(Long courseId) {
        return courseRepository.findById(courseId);
    }
}
