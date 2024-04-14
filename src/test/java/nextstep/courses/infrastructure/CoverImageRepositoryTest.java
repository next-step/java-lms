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

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImageRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImage coverImage = new CoverImage(9_000L, "GIF", 300L, 200L, 0L, 0L);
        int count = coverImageRepository.save(coverImage);
        assertThat(count).isEqualTo(1L);
        CoverImage savedCoverImage = coverImageRepository.findById(1L).get();
        assertThat(savedCoverImage.getCapacity().getCapacity()).isEqualTo(9_000L);
        assertThat(savedCoverImage.getType().name()).isEqualTo("GIF");
        assertThat(savedCoverImage.getDimension().getWidth()).isEqualTo(300L);
        assertThat(savedCoverImage.getDimension().getHeight()).isEqualTo(200L);
        LOGGER.debug("CoverImage: {}", savedCoverImage);
    }
}
