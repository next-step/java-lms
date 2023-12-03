package nextstep.courses.service;

import nextstep.courses.domain.Price;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.ImageRepository;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.domain.repository.SessionWithImageRepository;
import nextstep.courses.domain.repository.SessionWithNsUserRepository;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class SessionService {

    private SessionRepository sessionRepository;
    private SessionWithNsUserRepository sessionWithNsUserRepository;

    public SessionService(SessionRepository sessionRepository, SessionWithNsUserRepository sessionWithNsUserRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionWithNsUserRepository = sessionWithNsUserRepository;
    }

    public boolean enrollSession(NsUser nsUser, Long sessionId, Price paidFee) {
        Session session = sessionRepository.findById(sessionId);
        return sessionWithNsUserRepository.save(session.id(), nsUser.getId());
    }

    public void createSession(Session session) {
        sessionRepository.save(session);
    }

}
