package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseManageService {

    private final CourseRepository courseRepository;

    public CourseManageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(String title, Long creatorId) {
        Course course = new Course(title, creatorId);
        courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id);
    }
}
