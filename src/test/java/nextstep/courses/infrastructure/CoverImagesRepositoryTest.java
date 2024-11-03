package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImagesRepository;
import nextstep.courses.domain.session.CoverImages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.CoverImageTest.COVER_IMAGE_LIST1;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImagesRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImagesRepository coverImagesRepository;

    @BeforeEach
    void setUp() {
        coverImagesRepository = new JdbcCoverImagesRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImages coverImages = new CoverImages(COVER_IMAGE_LIST1.get(0));
        coverImages.add(COVER_IMAGE_LIST1.get(1));
        int freeSessionSavedCount = coverImagesRepository.saveAll(coverImages);
        assertThat(freeSessionSavedCount).isEqualTo(2);

        CoverImages savedCoverImages = coverImagesRepository.findAllBySessionId(1L);
        LOGGER.info("savedCoverImages = {}", savedCoverImages);
        LOGGER.info("coverImages = {}", coverImages);

        assertThat(coverImages.size()).isEqualTo(savedCoverImages.size());
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from cover_image");
        jdbcTemplate.execute("ALTER TABLE cover_image ALTER COLUMN id RESTART WITH 1");
    }
}
