package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    public void create(Course course) {
        courseRepository.save(course);
    }

    public void addSession(long courseId, Session session) {
        Course course = getCourse(courseId);
        course.addSession(session);
        sessionRepository.updateCourse(courseId, session);
    }

    private Course getCourse(long courseId) {
        return courseRepository.findById(courseId);
    }
}
