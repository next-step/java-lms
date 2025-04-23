package nextstep.courses.factory;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionEntity;
import nextstep.stub.domain.TestSessionImage;
import nextstep.stub.factory.TestSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.session.image.SessionImageType.JPEG;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionFactoryTest {

    @DisplayName("Session DB 정보로 Session 인스턴스 생성")
    @Test
    public void testCreateSession() throws IOException {
        SessionEntity sessionEntity = createSessionEntity(1L);
        SessionFactory sessionFactory = new TestSessionFactory();
        SessionImage image = new TestSessionImage("https://test", JPEG, 300, 200, 1024L * 866L);

        assertDoesNotThrow(() -> sessionFactory.createSession(sessionEntity, new SessionImages(List.of(image))));
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
