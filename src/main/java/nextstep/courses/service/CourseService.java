package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("courseService")
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SessionService sessionService;

    public CourseService(CourseRepository courseRepository, SessionService sessionService) {
        this.courseRepository = courseRepository;
        this.sessionService = sessionService;
    }

    @Transactional
    public Course registerSession(Long courseId, Long sessionId) {
        Session session = sessionService.findBySessionId(sessionId);
        Course course = courseRepository.findById(courseId);

        course.addSession(session);
        return course;
    }
}
