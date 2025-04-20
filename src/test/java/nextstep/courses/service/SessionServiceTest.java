package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.stub.TestSessionFactory;
import nextstep.stub.TestSessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionServiceTest {

    @DisplayName("Session 생성하기")
    @Test
    void testCreateSession() throws IOException {
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        TestSessionFactory sessionFactory = new TestSessionFactory();

        SessionService sessionService = new SessionService(sessionRepository, sessionFactory);

        SessionConstraint constraint = new SessionConstraint(200_000, 1);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy()
        );

        sessionService.createSession(1L, constraint, descriptor);

        assertThat(sessionRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("Session 삭제")
    @Test
    void testDeleteSession() throws IOException {
        TestSessionRepository sessionRepository = new TestSessionRepository(1L, null, List.of());
        SessionConstraint constraint = new SessionConstraint(200_000, 1);
        SessionDescriptor descriptor = new SessionDescriptor(
            new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new SessionEnrollPolicy()
        );
        Session session = new Session("1", constraint, descriptor);
        TestSessionFactory sessionFactory = new TestSessionFactory(session);

        SessionService sessionService = new SessionService(sessionRepository, sessionFactory);

        sessionService.deleteSession(1L);

        assertAll(
            () -> assertThat(sessionFactory.getCreateSessionCalled()).isEqualTo(1),
            () -> assertThat(session.isDeleted()).isTrue()
        );
    }
}
