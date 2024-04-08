package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Enrollment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.infrastructore.EnrollmentRepository;
import nextstep.sessions.infrastructore.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final EnrollmentRepository enrollmentRepository;

    public SessionService(SessionRepository sessionRepository, EnrollmentRepository enrollmentRepository) {
        this.sessionRepository = sessionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void requestJoin(Session session, NsUser loginUser) {
        LocalDateTime now = LocalDateTime.now();
        Enrollment enrollment = session.requestJoin(loginUser, now);
        enrollmentRepository.save(enrollment);

        if (session.isAutomaticSelection()) {
            Payment payment = session.toPayment(loginUser, now);
            // 저장 후 결제 요청
        }
    }

    public void joinSession(Session session, NsUser loginUser, Payment payment) {
        session.join(loginUser, payment);
    }
}
