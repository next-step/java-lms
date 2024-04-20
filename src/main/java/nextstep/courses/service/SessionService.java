package nextstep.courses.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.courses.domain.course.Sessions;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.session.SessionEntity;
import nextstep.courses.infrastructure.session.SessionRepository;
import nextstep.payments.domain.Payment;

@Service("SessionService")
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CoverImageService coverImageService;

    public SessionService(final SessionRepository sessionRepository, final CoverImageService coverImageService) {
        this.sessionRepository = sessionRepository;
        this.coverImageService = coverImageService;
    }

    @Transactional
    public Long save(final Session session) {
        final Long savedSessionId = sessionRepository.save(SessionEntity.fromDomain(session));
        coverImageService.save(session.coverImage(), savedSessionId);

        return savedSessionId;
    }

    public Session findById(final Long sessionId) {
        final SessionEntity sessionEntity = sessionRepository.findById(sessionId)
                .orElseThrow(NoSuchElementException::new);

        final Session session = sessionEntity.toDomain();
        session.assignCoverImage(coverImageService.findBySessionId(sessionId));

        return session;
    }

    public Sessions findAllByCourseId(final Long courseId) {
        final List<Session> sessions = sessionRepository.findAllByCourseId(courseId)
                .stream()
                .map(SessionEntity::toDomain)
                .collect(Collectors.toList());

        sessions.forEach(session -> session.assignCoverImage(coverImageService.findBySessionId(session.id())));

        return new Sessions(sessions);
    }

    @Transactional
    public void update(final Session session) {
        final SessionEntity sessionEntity = SessionEntity.fromDomain(session);
        sessionEntity.updateCourseId(session.course().id());
        sessionRepository.update(sessionEntity);
    }

    @Transactional
    public void enroll(final Long sessionId, final Payment payment) {
        final Session session = findById(sessionId);
        session.enroll(payment);
        sessionRepository.update(SessionEntity.fromDomain(session));
    }
}
