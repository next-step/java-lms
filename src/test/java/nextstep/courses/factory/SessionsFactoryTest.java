package nextstep.courses.factory;

import nextstep.courses.domain.session.SessionEntityImageMap;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.stub.factory.TestSessionFactory;
import nextstep.stub.factory.TestSessionsFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionsFactoryTest {

    @DisplayName("Session DB 정보들로 Sessions 인스턴스 생성")
    @Test
    public void testCreateSessions() {
        SessionFactory sessionFactory = new TestSessionFactory();
        SessionsFactory sessionsFactory = new TestSessionsFactory(
            sessionFactory, new Sessions()
        );

        SessionEntity sessionEntity = createSessionEntity(1L);
        SessionImageEntity sessionImageEntity = createSessionImageEntity(1L, "http://test", "JPG", 1L);

        assertDoesNotThrow(() -> sessionsFactory.create(new SessionEntityImageMap(Map.of(sessionEntity, List.of(sessionImageEntity)))));
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

    private SessionImageEntity createSessionImageEntity(Long id, String imageUrl, String imageType, Long sessionId) {
        return SessionImageEntity.builder()
            .id(id)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .imageUrl(imageUrl)
            .imageType(imageType)
            .sessionId(sessionId)
            .build();
    }
}

