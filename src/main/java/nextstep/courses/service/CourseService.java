package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.ragistration.Registration;
import nextstep.courses.domain.session.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final SessionService sessionService;
    private final RegistrationService registrationService;


    public CourseService(CourseRepository courseRepository, SessionService sessionService, RegistrationService registrationService) {
        this.courseRepository = courseRepository;
        this.sessionService = sessionService;
        this.registrationService = registrationService;
    }

    public void registerSession(long courseId, long sessionId, long userId) {
        final Course course = courseRepository.findById(courseId);
        final Session session = sessionService.findSessionById(sessionId);
        course.receiveSession(List.of(session));

        course.checkIsRegisterSession(sessionId, 1);

        registrationService.save(Registration.receipt(courseId, sessionId, userId));
    }
}
