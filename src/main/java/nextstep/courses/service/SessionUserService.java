package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionUser;
import nextstep.courses.domain.session.SessionUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionUserService {

    private final SessionRepository sessionRepository;
    private final SessionUserRepository sessionUserRepository;

    public SessionUserService(SessionRepository sessionRepository, SessionUserRepository sessionUserRepository) {
        this.sessionRepository = sessionRepository;
        this.sessionUserRepository = sessionUserRepository;
    }

    public List<SessionUser> findBySessionId(Long sessionId) {
        return sessionUserRepository.findBySessionId(sessionId);
    }

    public void save(Long sessionId, Long userId) {
        Session session = sessionRepository.findById(sessionId);
        SessionUser sessionUser = session.register(userId);
        sessionUserRepository.save(sessionUser);
    }
}
