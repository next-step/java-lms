package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.stub.factory.TestSessionFactory;
import nextstep.stub.repository.TestSessionImageRepository;
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

    @DisplayName("Session 저장")
    @Test
    void testCreateSession() {
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        TestSessionFactory sessionFactory = new TestSessionFactory();
        SessionService sessionService = new SessionService(sessionRepository, sessionFactory, new TestSessionImageService());
        SessionConstraint constraint = new SessionConstraint(200_000, 1);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy(),
            new SessionImages()
        );

        sessionService.saveSession(1L, constraint, descriptor);

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
}
