package nextstep.courses.service;

import nextstep.courses.domain.CourseRepository;
import nextstep.users.domain.NsUser;

public class CourseService {

    private CourseRepository courseRepository;
    private SessionService sessionService;

    public CourseService(CourseRepository courseRepository, SessionService sessionService) {
        this.courseRepository = courseRepository;
        this.sessionService = sessionService;
    }

    public void enroll(NsUser user, Long sessionId) {
        sessionService.enroll(user, sessionId);
    }
}
