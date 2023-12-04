package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;

public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void enrollSession(NsUser nsUser, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.addUser(nsUser);
        sessionRepository.save(session);
    }
}
