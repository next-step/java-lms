package nextstep.courses.service;


import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    public Course registerSession(long courseId, int sessionId) {
        Session session = sessionService.findById(sessionId);
        Course course = courseRepository.findById(courseId);
        course.registerSession(session);
        return course;
    }
}
