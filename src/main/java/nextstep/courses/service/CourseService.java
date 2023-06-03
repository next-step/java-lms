package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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

    public Course registerSession(Long courseId, Long sessionId) {
        Course course = courseRepository.findById(courseId);
        Session session = sessionService.findBySessionId(sessionId);

        course.addSession(session);
        return course;
    }
}
