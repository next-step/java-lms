package nextstep.courses.integration;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class SessionServiceIntegrationTest {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionRepository sessionRepository;

    @Transactional
    @Test
    void testGetSession() throws IOException {
        Long courseId = 2L;
        SessionConstraint constraint = new SessionConstraint(200_000L, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        sessionService.createSession(courseId, new Session(constraint, descriptor));
        List<SessionEntity> sessions = sessionRepository.findAllByCourseId(courseId);
        long sessionId = Long.parseLong(sessions.get(0).getId());

        Session session = sessionService.getSession(sessionId);

        assertThat(session).isNotNull();
    }

    @Transactional
    @Test
    void testSaveSession() {
        Long courseId = 1L;
        SessionConstraint constraint = new SessionConstraint(200_000L, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(),
            new SessionEnrollPolicy(),
            new SessionImages()
        );

        Long generatedId = sessionService.createSession(courseId, new Session(constraint, descriptor));

        SessionEntity sessions = sessionRepository.findById(generatedId);
        assertThat(sessions).isNotNull();
    }

    @Transactional
    @Test
    void testDeleteSession() {
        Long courseId = 3L;
        SessionConstraint constraint = new SessionConstraint(200_000L, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        sessionService.createSession(courseId, new Session(constraint, descriptor));
        List<SessionEntity> sessions = sessionRepository.findAllByCourseId(courseId);
        long sessionId = Long.parseLong(sessions.get(0).getId());

        sessionService.deleteSession(sessionId);

        SessionEntity deletedEntity = sessionRepository.findById(sessionId);
        assertThat(deletedEntity).isNull();
    }

    @Transactional
    @Test
    void testDeleteSessions() {
        Long courseId = 4L;
        SessionConstraint constraint = new SessionConstraint(200_000L, 80);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        sessionService.createSession(courseId, new Session(constraint, descriptor));
        sessionService.createSession(courseId, new Session(constraint, descriptor));

        sessionService.deleteSessions(courseId);

        List<SessionEntity> sessions = sessionRepository.findAllByCourseId(courseId);
        assertThat(sessions).isEmpty();
    }
}
