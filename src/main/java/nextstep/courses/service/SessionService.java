package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.SessionRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    public void createSession(Long courseId, Session session) {
        Course course = courseRepository.findById(courseId);
        course.addSession(session);

        sessionRepository.save(session);
    }

    public void register(Long id, Payment payment) {
        Session session = sessionRepository.findById(id);
        session.register(payment);
    }
}
