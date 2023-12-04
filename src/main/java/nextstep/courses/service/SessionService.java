package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.Session;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.SessionRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

@Service("sessionService")
public class SessionService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    public void createFreeSession(Long courseId, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        Course course = courseRepository.findById(courseId);

        Session session = new Session(0L, coverImage, startDate, endDate);
        course.addSession(session);

        sessionRepository.save(session);
    }

    public void createPaidSession(Long courseId, CoverImage coverImage, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee) {
        Course course = courseRepository.findById(courseId);

        Session session = new PaidSession(0L, coverImage, startDate, endDate, maxStudents, fee);
        course.addSession(session);

        sessionRepository.save(session);
    }

    public void registerSession(Long sessionId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        session.register(payment);
    }
}
