package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
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
        course.receiveSession(List.of(sessionService.findSessionById(sessionId)));

        course.registerSession(sessionId, 1);

        courseRepository.save(course);
    }
}
