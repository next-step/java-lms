package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.ImageExtension;
import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.infrastructure.engine.SessionCoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.SessionCoverImageFixture.coverImage;
import static nextstep.courses.domain.fixture.SessionCoverImageFixture.coverImages;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SessionCoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionCoverImageRepository sessionCoverImageRepository;

    @BeforeEach
    void setUp() {
        sessionCoverImageRepository = new JdbcSessionCoverImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("saveAll()")
    void saveAll() {
        SessionCoverImage gif = coverImage(ImageExtension.GIF);
        SessionCoverImage jpg = coverImage(ImageExtension.JPG);
        List<SessionCoverImage> image = coverImages(gif, jpg);

        int[] affectedRows = sessionCoverImageRepository.saveAll(image);

        assertThat(affectedRows).hasSize(2);
    }

    @Test
    @DisplayName("findAllBySessionId()")
    void findAllBySessionId() {
        SessionCoverImage gif = coverImage(SESSION_ID, ImageExtension.GIF);
        SessionCoverImage jpg = coverImage(SESSION_ID, ImageExtension.JPG);
        List<SessionCoverImage> image = coverImages(gif, jpg);
        sessionCoverImageRepository.saveAll(image);

        List<SessionCoverImage> images = sessionCoverImageRepository.findAllBySessionId(SESSION_ID);

        assertThat(images).hasSize(2);
    }

}
