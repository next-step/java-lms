package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.session.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final SessionService sessionService;


    public CourseService(CourseRepository courseRepository, SessionService sessionService) {
        this.courseRepository = courseRepository;
        this.sessionService = sessionService;
    }

    public void registerSession(long courseId, long sessionId) {
        final Course course = courseRepository.findById(courseId);
        final Session session = sessionService.findSessionById(sessionId);
        course.receiveSession(List.of(session));

        course.registerSession(sessionId, 1);

        sessionService.update(session, courseId);
    }
}
