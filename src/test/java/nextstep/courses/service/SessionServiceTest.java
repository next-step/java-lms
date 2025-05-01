package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.courses.entity.SessionEntity;
import nextstep.stub.factory.TestSessionFactory;
import nextstep.stub.repository.TestSessionRepository;
import nextstep.stub.service.TestSessionImageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionServiceTest {

    @DisplayName("Session 조회")
    @Test
    void testGetSession() throws IOException {
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        SessionConstraint constraint = new SessionConstraint(200_000, 1);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Session session = new Session("1", constraint, descriptor);
        TestSessionFactory sessionFactory = new TestSessionFactory(session);
        TestSessionImageService sessionImageService = new TestSessionImageService();
        SessionService sessionService = new SessionService(sessionRepository, sessionFactory, sessionImageService);

        sessionService.getSession(1L);

        assertThat(sessionFactory.getCreateSessionCalled()).isEqualTo(1);
    }

    @DisplayName("Session 저장")
    @Test
    void testSaveSession() {
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        SessionConstraint constraint = new SessionConstraint(200_000, 1);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Session session = new Session("1", constraint, descriptor);
        TestSessionFactory sessionFactory = new TestSessionFactory(session);
        TestSessionImageService sessionImageService = new TestSessionImageService();
        SessionService sessionService = new SessionService(sessionRepository, sessionFactory, sessionImageService);

        sessionService.createSession(1L, session);

        assertThat(sessionRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("Session 삭제")
    @Test
    void testDeleteSession() {
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        SessionConstraint constraint = new SessionConstraint(200_000, 1);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Session session = new Session("1", constraint, descriptor);
        TestSessionFactory sessionFactory = new TestSessionFactory(session);
        TestSessionImageService sessionImageService = new TestSessionImageService();
        SessionService sessionService = new SessionService(sessionRepository, sessionFactory, sessionImageService);

        sessionService.deleteSession(1L);

        assertAll(
            () -> assertThat(sessionRepository.getDeleteCalled()).isEqualTo(1),
            () -> assertThat(sessionImageService.getDeleteSessionImagesCalled()).isEqualTo(1)
        );
    }

    @DisplayName("Course의 모든 Session 삭제")
    @Test
    void testDeleteSessions() {
        TestSessionRepository sessionRepository = new TestSessionRepository(
            1L,
            null,
            List.of(createSessionEntity(1L), createSessionEntity(1L))
        );
        SessionConstraint constraint = new SessionConstraint(200_000, 1);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy(),
            new SessionImages()
        );
        Session session = new Session("1", constraint, descriptor);
        TestSessionFactory sessionFactory = new TestSessionFactory(session);
        TestSessionImageService sessionImageService = new TestSessionImageService();

        SessionService sessionService = new SessionService(sessionRepository, sessionFactory, sessionImageService);

        sessionService.deleteSessions(1L);

        assertAll(
            () -> assertThat(sessionRepository.getDeleteCalled()).isEqualTo(2),
            () -> assertThat(sessionImageService.getDeleteSessionImagesCalled()).isEqualTo(2)
        );
    }

    private SessionEntity createSessionEntity(Long id) {
        return SessionEntity.builder()
            .id(id)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .courseId(1L)
            .fee(200_000L)
            .capacity(80)
            .imageUrl("http://test")
            .imageType("JPG")
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .type("PAID")
            .status("ONGOING")
            .enrollStatus("ENROLLING")
            .build();
    }
}
