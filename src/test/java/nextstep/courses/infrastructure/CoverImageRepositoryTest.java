package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.repository.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class CoverImageRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImage image = new CoverImage(1, "jpg", 300, 200);
        int count = coverImageRepository.save(image);
        assertThat(count).isEqualTo(1);
        CoverImage savedImage = coverImageRepository.findById(1L);
        assertThat(savedImage.getId()).isEqualTo(image.getId());
        LOGGER.debug("CoverImage: {}", savedImage);
    }

    @Test
    void find() {
        assertThat(coverImageRepository.findById(2L).getId()).isEqualTo(2L);
    }
}
