package nextstep.courses.service;

import nextstep.courses.domain.Price;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.domain.repository.SessionNsUserRepository;
import nextstep.users.domain.NsUser;

public class SessionService {

    private SessionRepository sessionRepository;
    private SessionNsUserRepository sessionNsUserRepository;

    public SessionService(SessionRepository sessionRepository, SessionNsUserRepository sessionNsUserRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionNsUserRepository = sessionNsUserRepository;
    }

    public boolean enrollSession(NsUser nsUser, Long sessionId, Price paidFee) {
        Session session = sessionRepository.findById(sessionId);
        return sessionNsUserRepository.save(session.id(), nsUser.getId());
    }

    public void createSession(Session session) {
        sessionRepository.save(session);
    }

}
