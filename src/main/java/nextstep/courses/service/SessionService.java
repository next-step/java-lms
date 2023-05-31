package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    @Transactional(readOnly = true)
    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Transactional
    public long enroll(Session session, NsUser nsUser) {
        session.enrollNsUser(nsUser);
        return sessionRepository.saveNsUser(session, nsUser);
    }

    @Transactional(readOnly = true)
    public List<NsUser> findAllUserBySessionId(Long sessionId) {
        return sessionRepository.findAllUserBySessionId(sessionId);
    }
}
