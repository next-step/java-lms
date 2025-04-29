package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.factory.CourseFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseFactory courseFactory;
    private final SessionService sessionService;

    public CourseService(CourseRepository courseRepository, CourseFactory courseFactory, SessionService sessionService) {
        this.courseRepository = courseRepository;
        this.courseFactory = courseFactory;
        this.sessionService = sessionService;
    }

    public Long createCourse(String title, Long creatorId) {
        Course course = new Course(title, creatorId);
        return courseRepository.save(courseFactory.createCourseEntity(course));
    }

    @Transactional
    public void deleteCourse(long courseId) {
        sessionService.deleteSessions(courseId);
        courseRepository.delete(courseId);
    }
}
