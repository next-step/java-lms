package nextstep.courses.service;


import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.exception.InvalidSessionDateTimeException;
import nextstep.courses.exception.SessionEnrollmentException;
import nextstep.users.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sessionService")
@Transactional(readOnly = true)
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Transactional
    public void enrollSession(Long sessionId, User user) throws SessionEnrollmentException {
        Session session = sessionRepository.findById(sessionId);
        session.enroll(user);
        sessionRepository.update(session);
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Transactional
    public void createSession(Session session) throws InvalidSessionDateTimeException {
        sessionRepository.save(session);
    }

    @Transactional
    public void updateSession(Session session) {
        sessionRepository.update(session);
    }

    @Transactional
    public void deleteSession(Long sessionId) {
        sessionRepository.delete(sessionId);
    }
}
