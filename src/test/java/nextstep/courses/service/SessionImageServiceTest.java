package nextstep.courses.service;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.stub.domain.TestSessionImage;
import nextstep.stub.factory.TestSessionImageFactory;
import nextstep.stub.repository.TestSessionImageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.session.image.SessionImageType.JPEG;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionImageServiceTest {

    @DisplayName("session image 조회")
    @Test
    void testGetSessionImages() throws IOException {
        long sessionId = 101L;
        String imageUrl = "https://test.com";
        TestSessionImageRepository sessionImageRepository =
            new TestSessionImageRepository(List.of(createSessionImageEntity(null, imageUrl, sessionId)));
        SessionImage sessionImage =
            new TestSessionImage("test", JPEG, 200, 300, 1024L * 825L);
        TestSessionImageFactory sessionImageFactory = new TestSessionImageFactory(null, sessionImage);
        SessionImageService sessionImageService = new SessionImageService(sessionImageRepository, sessionImageFactory);

        assertThat(sessionImageService.getSessionImages(1L).size()).isEqualTo(1);
    }

    @DisplayName("session image 저장")
    @Test
    void testCreateSessionImage() throws IOException {
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository();
        SessionImage sessionImage = new TestSessionImage("test", JPEG, 200, 300, 1024L * 825L);
        TestSessionImageFactory sessionImageFactory = new TestSessionImageFactory(null, sessionImage);
        SessionImageService sessionImageService = new SessionImageService(sessionImageRepository, sessionImageFactory);

        sessionImageService.createSessionImage(1L, "test-url", "png");

        assertThat(sessionImageRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("session image 삭제")
    @Test
    void testDeleteSessionImage() throws IOException {
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository();
        SessionImage result = new TestSessionImage("test", JPEG, 200, 300, 1024L * 825L);
        TestSessionImageFactory sessionImageFactory = new TestSessionImageFactory(result);
        SessionImageService sessionImageService = new SessionImageService(sessionImageRepository, sessionImageFactory);

        sessionImageService.deleteSessionImage(1L);

        assertThat(sessionImageRepository.getDeleteCalled()).isEqualTo(1);
    }

    @DisplayName("session image 복수개 삭제")
    @Test
    void testDeleteSessionImages() throws IOException {
        TestSessionImageRepository sessionImageRepository =
            new TestSessionImageRepository(List.of(
                createSessionImageEntity(1L, "https://test.com", 101L),
                createSessionImageEntity(2L, "https://test.com", 102L)
            ));

        SessionImage result = new TestSessionImage("test", JPEG, 200, 300, 1024L * 825L);
        TestSessionImageFactory sessionImageFactory = new TestSessionImageFactory(result);
        SessionImageService sessionImageService = new SessionImageService(sessionImageRepository, sessionImageFactory);

        sessionImageService.deleteSessionImages(1L);

        assertThat(sessionImageRepository.getDeleteCalled()).isEqualTo(2);
    }

    private SessionImageEntity createSessionImageEntity(Long id, String url, Long sessionId) {
        return SessionImageEntity.builder()
            .id(id)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .imageUrl(url)
            .imageType("PNG")
            .sessionId(sessionId)
            .build();
    }
}
