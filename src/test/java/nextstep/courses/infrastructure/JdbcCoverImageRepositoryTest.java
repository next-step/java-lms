package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.CoverImageRepository;
import nextstep.courses.domain.session.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcCoverImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcCoverImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        final long sessionId = 2L;
        CoverImage coverImage = new CoverImage(1000, ImageType.JPG, 300, 200);
        int count = coverImageRepository.save(coverImage, sessionId);
        assertThat(count).isEqualTo(1);

        CoverImage savedCoverImage = coverImageRepository.findBySessionId(sessionId);
        assertThat(coverImage.getImageTypeString()).isEqualTo(savedCoverImage.getImageTypeString());
        LOGGER.debug("CoverImage Type: {}", savedCoverImage);
    }
}
