package nextstep.courses.factory;

import nextstep.courses.domain.session.Sessions;
import nextstep.courses.entity.SessionEntity;
import nextstep.stub.TestSessionFactory;
import nextstep.stub.TestSessionsFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionsFactoryTest {

    @DisplayName("Session DB 정보들로 Sessions 인스턴스 생성")
    @Test
    public void testCreateSessions() {
        List<SessionEntity> sessionEntities = List.of(createSessionEntity(1L), createSessionEntity(2L));
        SessionFactory sessionFactory = new TestSessionFactory();
        SessionsFactory sessionsFactory = new TestSessionsFactory(
            sessionFactory, new Sessions()
        );

        assertDoesNotThrow(() -> sessionsFactory.create(sessionEntities));
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
            .status("ENROLLING")
            .build();
    }
}
