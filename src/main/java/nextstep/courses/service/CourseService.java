package nextstep.courses.service;

import java.util.Optional;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;

import nextstep.courses.domain.Sessions;
import nextstep.users.domain.NsUser;

public class CourseService {

    private CourseRepository courseRepository;
    private SessionService sessionService;

    public CourseService(CourseRepository courseRepository, SessionService sessionService) {
        this.courseRepository = courseRepository;
        this.sessionService = sessionService;
    }

    public void enroll(NsUser user, Long courseId, Long sessionId) {
        Course course = courseRepository.findById(courseId);
        Optional<Sessions> sessions = sessionService.sessions(courseId);
        if (sessions.isPresent()) {
            course.addSessions(sessions.get());
            sessionService.enroll(user, course.enroll(user, sessionId).toDto());
        }
    }
}
