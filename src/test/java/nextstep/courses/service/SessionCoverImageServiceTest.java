package nextstep.courses.service;

import nextstep.courses.domain.session.SessionCoverImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SessionCoverImageServiceTest {

    @Autowired
    SessionCoverImageService sessionCoverImageService;

    @Test
    void save() {
        Long sessionId = 1L;
        String name = "test.jpg";
        String path = "/";
        SessionCoverImage sessionCoverImage = new SessionCoverImage(1L, "/", "test.jpg");
        int save = sessionCoverImageService.save(sessionCoverImage, sessionId);

        Assertions.assertThat(save).isEqualTo(1);

        SessionCoverImage coverImage = sessionCoverImageService.findById(1L);
        Assertions.assertThat(coverImage.getName()).isEqualTo(name);
        Assertions.assertThat(coverImage.getPath()).isEqualTo(path);
    }
}
