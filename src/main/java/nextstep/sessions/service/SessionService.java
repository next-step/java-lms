package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.infrastructure.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
public class SessionService {
    private SessionRepository sessionRepository;

    @Transactional
    public void enroll(Payment payment) {
        Session session = sessionRepository.findById(payment.getSessionId());
        session.enroll();
        sessionRepository.enroll(session);
    }
}
