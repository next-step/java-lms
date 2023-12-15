package nextstep.enrolment.facade;

import nextstep.courses.domain.Course;
import nextstep.courses.service.CourseService;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.sessions.domain.Session;
import nextstep.sessions.service.SessionService;

import java.util.List;

public class EnrolmentFacade {

    private final CourseService courseService;
    private final SessionService sessionService;
    private final PaymentService paymentService;

    public EnrolmentFacade(CourseService courseService,
                           SessionService sessionService,
                           PaymentService paymentService) {
        this.courseService = courseService;
        this.sessionService = sessionService;
        this.paymentService = paymentService;
    }

    public Course findCourseByCardinalNumber(int cardinalNumber) {
        return courseService.findByCardinalNumber(cardinalNumber);
    }

    public Session findSessionById(Long sessionId) {
        return sessionService.findById(sessionId);
    }

    public List<Payment> findPaymentsBySessionId(Long sessionId) {
        return paymentService.findBySessionId(sessionId);
    }

    public Payment findPaymentBySessionIdAndNsUserId(Long sessionId, Long nsUserId) {
        return paymentService.findBySessionIdAndNsUserId(sessionId, nsUserId);
    }
}
