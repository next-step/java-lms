package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.factory.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionImageRepository sessionImageRepository;
    private final SessionFactory sessionFactory;

    public SessionService(
        SessionRepository sessionRepository,
        SessionImageRepository sessionImageRepository,
        SessionFactory sessionFactory
    ) {
        this.sessionRepository = sessionRepository;
        this.sessionImageRepository = sessionImageRepository;
        this.sessionFactory = sessionFactory;
    }

    public void saveSession(Long courseId, SessionConstraint constraint, SessionDescriptor descriptor) {
        Session newSession = new Session(constraint, descriptor);
        sessionRepository.save(sessionFactory.createSessionEntity(newSession, courseId));
    }

    public Session createSession(long sessionId) throws IOException {
        return sessionFactory.createSession(sessionRepository.findById(sessionId), sessionImageRepository.findAllBySessionId(sessionId));
    }

    @Transactional
    public void deleteSession(long sessionId) throws IOException {
        Session session = sessionFactory.createSession(
            sessionRepository.findById(sessionId),
            sessionImageRepository.findAllBySessionId(sessionId)
        );
        session.delete();
    }
}
