package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.factory.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionFactory sessionFactory;
    private final SessionImageService sessionImageService;

    public SessionService(
        SessionRepository sessionRepository,
        SessionFactory sessionFactory,
        SessionImageService sessionImageService
    ) {
        this.sessionRepository = sessionRepository;
        this.sessionFactory = sessionFactory;
        this.sessionImageService = sessionImageService;
    }

    public Session getSession(long sessionId) throws IOException {
        return sessionFactory.createSession(
            sessionRepository.findById(sessionId),
            sessionImageService.getSessionImages(sessionId)
        );
    }

    public Long createSession(Long courseId, Session session) {
        return sessionRepository.save(sessionFactory.createSessionEntity(session, courseId));
    }

    @Transactional
    public void deleteSession(long sessionId) {
        sessionImageService.deleteSessionImages(sessionId);
        sessionRepository.delete(sessionId);
    }

    @Transactional
    public void deleteSessions(long courseId) {
        List<SessionEntity> sessionEntities = getSessionEntities(courseId);

        sessionEntities.stream()
            .map(SessionEntity::getId)
            .map(Long::parseLong)
            .forEach(this::deleteSession);
    }

    private List<SessionEntity> getSessionEntities(long courseId) {
        return sessionRepository.findAllByCourseId(courseId);
    }
}
