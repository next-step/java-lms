package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class SessionService {
    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void enroll(NsUser nsUser, Long sessionId, Payment payment) {
        Session session = sessionRepository.findById(sessionId);
        session.enroll(nsUser, payment);
    }

    public void create(Session session) {
        sessionRepository.save(session);
    }
}
