package nextstep.courses.factory;

import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.stub.TestImageHandler;
import nextstep.stub.TestSessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionImageFactoryTest {

    @DisplayName("Session, SessionImage DB 정보로 SessionImage 인스턴스 생성 - Session DB에 이미지 정보가 없는 경우")
    @Test
    public void testCreate_NoSessionImageInSession() throws IOException {
        SessionEntity sessionEntity = createSessionEntity(1L, null, null);
        SessionImageEntity sessionImageEntity = createSessionImageEntity(1L, "http://test", "JPG", 1L);

        SessionImageFactory sessionImageFactory = new SessionImageFactory(
            new TestImageHandler(), new TestSessionFactory()
        );

        assertThat(sessionImageFactory.create(List.of(sessionImageEntity), sessionEntity).size())
            .isEqualTo(1);
    }

    @DisplayName("Session, SessionImage DB 정보로 SessionImage 인스턴스 생성 - Session DB에 이미지 정보가 있는 경우")
    @Test
    public void testCreate_SessionImageInSession() throws IOException {
        SessionEntity sessionEntity = createSessionEntity(1L, "http://test2", "PNG");
        SessionImageEntity sessionImageEntity = createSessionImageEntity(1L, "http://test2", "JPG", 1L);

        SessionImageFactory sessionImageFactory = new SessionImageFactory(
            new TestImageHandler(), new TestSessionFactory()
        );

        assertThat(sessionImageFactory.create(List.of(sessionImageEntity), sessionEntity).size())
            .isEqualTo(2);
    }

    private SessionEntity createSessionEntity(Long id, String imageUrl, String imageType) {
        return SessionEntity.builder()
            .id(id)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .courseId(1L)
            .fee(200_000L)
            .capacity(80)
            .imageUrl(imageUrl)
            .imageType(imageType)
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .type("PAID")
            .status("ENROLLING")
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
