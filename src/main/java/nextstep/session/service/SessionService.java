package nextstep.session.service;

import nextstep.courses.domain.Course;
import nextstep.courses.service.CourseService;
import nextstep.payments.service.PaymentService;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.ui.CreateSessionRequest;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    @Resource(name = "courseService")
    private CourseService courseService;
    private PaymentService paymentService;

    public Session createSession(NsUser loginUser, CreateSessionRequest dto) {
        Course course = courseService.findById(dto.getCourseId());
        Session session = dto.toSession(loginUser);
        sessionRepository.save(session);
        course.addSession(session);
        return session;
    }

    public Session findById(Long id) {
        return sessionRepository.findById(id);
    }

    public void enrollSession(NsUser loginUser, Long sessionId) {
        Session session = findById(sessionId);
        session.enroll(loginUser);
    }
}
