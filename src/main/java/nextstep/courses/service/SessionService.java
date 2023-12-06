package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.FreeSessionRepository;
import nextstep.courses.repository.PaidSessionRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

@Service("sessionService")
public class SessionService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Resource(name = "freeSessionRepository")
    private FreeSessionRepository freeSessionRepository;

    @Resource(name = "paidSessionRepository")
    private PaidSessionRepository paidSessionRepository;

    public void createFreeSession(Long courseId, CoverImage coverImage, LocalDate startDate, LocalDate endDate) {
        Course course = courseRepository.findById(courseId);

        Session session = new FreeSession(0L, coverImage, startDate, endDate);
        course.addSession(session);

        freeSessionRepository.save(session, courseId);
    }

    public void createPaidSession(Long courseId, CoverImage coverImage, LocalDate startDate, LocalDate endDate, int maxStudents, Long fee) {
        Course course = courseRepository.findById(courseId);

        PaidSession session = new PaidSession(0L, coverImage, startDate, endDate, maxStudents, fee);
        course.addSession(session);

        paidSessionRepository.save(session, courseId);
    }

    public void registerSession(Long sessionId, Payment payment) {
        Session session = freeSessionRepository.findById(sessionId);
        session.register(payment);
    }
}
