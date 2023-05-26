package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public long save(Session session, Long courseId) {
        return sessionRepository.save(session, courseId);
    }

    @Transactional(readOnly = true)
    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Transactional(readOnly = true)
    public List<Session> findByCourseId(Long courseId) {
        return sessionRepository.findByCourseId(courseId);
    }

    @Transactional
    public long saveSessionUser(Session session, NsUser nextStepUser) {
        session.enroll(nextStepUser);
        return sessionRepository.saveSessionUser(session, nextStepUser);
    }

    @Transactional(readOnly = true)
    public List<NsUser> findAllUserBySessionId(Long sessionId) {
        return sessionRepository.findAllUserBySessionId(sessionId);
    }
}
