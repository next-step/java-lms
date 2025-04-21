package nextstep.courses.service;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.stub.factory.TestImageHandler;
import nextstep.stub.factory.TestSessionImageFactory;
import nextstep.stub.repository.TestSessionImageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static nextstep.courses.domain.session.image.SessionImageType.JPEG;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionImageServiceTest {

    @DisplayName("session image 만들기")
    @Test
    void testCreateSessionImage() throws IOException {
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository();
        TestSessionImageFactory sessionImageFactory = new TestSessionImageFactory();
        TestImageHandler imageHandler = new TestImageHandler();

        SessionImageService sessionImageService = new SessionImageService(sessionImageRepository, sessionImageFactory, imageHandler);

        sessionImageService.createSessionImage(1L, "test-url", "png");
        assertThat(sessionImageRepository.getSaveCalled()).isEqualTo(1);
    }

    @DisplayName("session image 삭제")
    @Test
    void testDeleteSessionImage() throws IOException {
        TestSessionImageRepository sessionImageRepository = new TestSessionImageRepository();
        SessionImage result = new SessionImage("test", new TestImageHandler(), JPEG);
        TestSessionImageFactory sessionImageFactory = new TestSessionImageFactory(result);
        TestImageHandler imageHandler = new TestImageHandler();

        SessionImageService sessionImageService = new SessionImageService(sessionImageRepository, sessionImageFactory, imageHandler);

        sessionImageService.deleteSessionImage(1L);

        assertThat(result.isDeleted()).isTrue();
    }

}
