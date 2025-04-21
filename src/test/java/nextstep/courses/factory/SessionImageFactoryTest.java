package nextstep.courses.factory;

import nextstep.courses.entity.SessionImageEntity;
import nextstep.stub.factory.TestImageHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionImageFactoryTest {

    @DisplayName("SessionImage DB 정보로 SessionImage 인스턴스 생성")
    @Test
    public void testCreate_NoSessionImageInSession() {
        SessionImageEntity sessionImageEntity = createSessionImageEntity(1L, "http://test", "JPG", 1L);

        SessionImageFactory sessionImageFactory = new SessionImageFactory(new TestImageHandler());

        assertDoesNotThrow(() -> sessionImageFactory.create(sessionImageEntity));
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
