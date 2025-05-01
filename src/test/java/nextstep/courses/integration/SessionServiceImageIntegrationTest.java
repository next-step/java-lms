package nextstep.courses.integration;

import nextstep.courses.domain.session.image.SessionImageRepository;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.entity.SessionImageEntity;
import nextstep.courses.service.SessionImageService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Disabled
public class SessionServiceImageIntegrationTest {

    @Autowired
    private SessionImageService sessionImageService;

    @Autowired
    private SessionImageRepository sessionImageRepository;

    @Transactional
    @Test
    void testCreateSessionImage() throws IOException {
        long sessionId = 100L;
        String imageUrl = "https://test.com"; // 유효한 이미지 URL 입력
        String imageType = "PNG";

        Long generatedId = sessionImageService.createSessionImage(sessionId, imageUrl, imageType);

        SessionImageEntity images = sessionImageRepository.findById(generatedId);
        assertThat(images).isNotNull();
    }

    @Transactional
    @Test
    void testGetSessionImages() throws IOException {
        long sessionId = 101L;
        String imageUrl = "https://test.com"; // 유효한 이미지 URL 입력

        sessionImageRepository.save(createSessionImageEntity(null, imageUrl, sessionId));

        SessionImages sessionImages = sessionImageService.getSessionImages(sessionId);

        assertThat(sessionImages.size()).isEqualTo(1);
    }

    @Transactional
    @Test
    void testDeleteSessionImages() {
        long sessionId = 103L;
        String imageUrl = "https://test.com"; // 유효한 이미지 URL 입력

        sessionImageRepository.save(createSessionImageEntity(null, imageUrl, sessionId));
        sessionImageRepository.save(createSessionImageEntity(null, imageUrl, sessionId));

        List<SessionImageEntity> imagesBeforeDeletion = sessionImageRepository.findAllBySessionId(sessionId);
        assertThat(imagesBeforeDeletion).hasSize(2);

        sessionImageService.deleteSessionImages(sessionId);

        List<SessionImageEntity> imagesAfterDeletion = sessionImageRepository.findAllBySessionId(sessionId);
        assertThat(imagesAfterDeletion).isEmpty();
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
