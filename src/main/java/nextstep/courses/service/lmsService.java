package nextstep.courses.service;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.session.Session;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.error.exception.NotExistSession;
import nextstep.courses.factory.SessionFactory;
import nextstep.courses.infrastructure.CourseRepository;
import nextstep.courses.infrastructure.ImageRepository;
import nextstep.courses.infrastructure.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class lmsService {

    private final PaymentService paymentService;

    private final CourseRepository courseRepository;

    private final SessionRepository sessionRepository;

    private final ImageRepository imageRepository;

    public lmsService(PaymentService paymentService, CourseRepository courseRepository,
        SessionRepository sessionRepository, ImageRepository imageRepository) {
        this.paymentService = paymentService;
        this.courseRepository = courseRepository;
        this.sessionRepository = sessionRepository;
        this.imageRepository = imageRepository;
    }

    public void enroll(Long sessionId, Enrollment enrollment){
        SessionEntity sessionEntity = sessionRepository.findById(sessionId)
            .orElseThrow(() -> new NotExistSession(sessionId));

        Payment payment = paymentService.payment(enrollment);
        Session session = SessionFactory.createSession(sessionEntity);

        enrollment.enroll(session, payment);

        sessionRepository.updateRegistrationCount(SessionEntity.from(session));
    }
}
