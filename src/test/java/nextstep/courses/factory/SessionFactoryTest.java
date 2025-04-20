package nextstep.courses.factory;

import nextstep.courses.entity.SessionEntity;
import nextstep.stub.TestImageHandler;
import nextstep.stub.TestSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionFactoryTest {

    @DisplayName("Session DB 정보로 Session 인스턴스 생성")
    @Test
    public void testCreateSession() {
        SessionEntity sessionEntity = createSessionEntity(1L);

        SessionFactory sessionFactory = new TestSessionFactory();
        assertDoesNotThrow(() -> sessionFactory.create(sessionEntity));
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
