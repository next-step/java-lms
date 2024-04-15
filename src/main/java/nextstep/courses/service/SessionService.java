package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service("sessionService")
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void enroll(Long sessionId, NsUser loginUser, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        session.enroll2(loginUser, payment);
        sessionRepository.update(session);
    }
}
