package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.enrollment.SessionStudent;
import nextstep.courses.infrastructure.engine.SessionCoverImageRepository;
import nextstep.courses.infrastructure.engine.SessionRepository;
import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionCoverImageRepository sessionCoverImageRepository;
    private final SessionStudentRepository sessionStudentRepository;
    private final PaymentService paymentService;

    public SessionService(SessionRepository sessionRepository, SessionCoverImageRepository sessionCoverImageRepository, SessionStudentRepository sessionStudentRepository, PaymentService paymentService) {
        this.sessionRepository = sessionRepository;
        this.sessionCoverImageRepository = sessionCoverImageRepository;
        this.sessionStudentRepository = sessionStudentRepository;
        this.paymentService = paymentService;
    }

    public void save(Session session) {
        sessionRepository.save(session);
        sessionCoverImageRepository.save(session.getCoverImage());
    }

    public void enroll(Long sessionId, NsUser user) {
        Session session = findSession(sessionId);

        Payment payment = paymentService.payment(sessionId, user.getId(), session.getEnrollment().getFee());
        SessionStudent student = session.enroll(user, payment);

        sessionStudentRepository.save(student);
    }

    private Session findSession(Long sessionId) {
        List<SessionStudent> findStudents = sessionStudentRepository.findAllBySessionId(sessionId);
        Session findSession = sessionRepository.findById(sessionId);
        return new Session(findSession, findStudents);
    }

}
