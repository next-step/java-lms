package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcCoverImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void save_find() {
        int count = coverImageRepository.save(NORMAL_COVER_IMAGE, 2L);
        assertThat(count).isEqualTo(1);
        CoverImage savedCoverImage = coverImageRepository.findBySessionId(2L);
        assertThat(savedCoverImage.size()).isEqualTo(NORMAL_COVER_IMAGE.size());
        LOGGER.debug("CoverImage : {}", savedCoverImage);
    }
}