package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;

public class SessionService {

    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public boolean enrollSession(NsUser nsUser, Long sessionId, int paidFee) {
        Session session = sessionRepository.findById(sessionId);
        return session.enroll(nsUser, paidFee);
    }

    public void createSession(Session session) {
        sessionRepository.save(session);
    }

}
