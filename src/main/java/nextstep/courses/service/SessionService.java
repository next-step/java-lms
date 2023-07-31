package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionUser;
import nextstep.courses.domain.session.SessionUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionUserRepository sessionUserRepository;

    public SessionService(SessionRepository sessionRepository, SessionUserRepository sessionUserRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionUserRepository = sessionUserRepository;
    }

    public Session findById(Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        List<SessionUser> sessionUsers = sessionUserRepository.findBySessionId(sessionId);
        sessionUsers.forEach(session::register);
        return session;
    }

    public void save(Session session) {
        sessionRepository.save(session);
    }
}
