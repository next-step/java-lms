package nextstep.session.service;

import nextstep.courses.domain.Course;
import nextstep.courses.sevice.CourseService;
import nextstep.session.domain.Session;
import nextstep.session.dto.CreateSessionRequest;
import nextstep.session.dto.EnrollSessionRequest;
import nextstep.session.repository.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.service.UserService;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserService userService;
    private final CourseService courseService;

    public SessionService(SessionRepository sessionRepository, UserService userService, CourseService courseService) {
        this.sessionRepository = sessionRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    public Long save(CreateSessionRequest request) {
        Course course = courseService.findCourse(request.getCourseId());
        Session session = request.toEntity();
        course.addSession(session);
        courseService.saveCourse(course);
        return sessionRepository.save(session);
    }

    public Session findSession(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    public void enrollSession(EnrollSessionRequest request) {
        Session session = findSession(request.getSessionId());
        NsUser user = userService.findUser(request.getUserId());
        session.enrollSession(request.getFee(), user);
        sessionRepository.save(session);
    }
}
