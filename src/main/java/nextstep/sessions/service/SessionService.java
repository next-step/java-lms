package nextstep.sessions.service;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.sessions.infrastructore.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Payment requestJoin(Session session, NsUser loginUser) {
        return session.requestJoin(loginUser, LocalDateTime.now());
    }

    public void joinSession(Session session, NsUser loginUser, Payment payment) {
        session.join(loginUser, payment);
    }
}
