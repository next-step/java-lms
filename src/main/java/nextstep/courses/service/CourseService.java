package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.course.session.Session;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("courseService")
public class CourseService {
    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    public void addSession(long courseId, Session session) {
        Course course = getCourse(courseId);
        course.addSession(session);
    }

    private Course getCourse(long courseId) {
        return courseRepository.findById(courseId);
    }
}
