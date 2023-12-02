package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.vo.Registration;
import nextstep.sessions.repository.SessionRepository;

public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void enroll(long sessionId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        Registration registration = session.registration(payment);
        sessionRepository.save(registration);
    }
}
