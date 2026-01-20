package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.CoverImageRepository;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class CoverImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoverImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void 커버_이미지를_저장할_수_있다() {
        CoverImage coverImage = new CoverImage(1L, 1_048_576L, ImageType.JPG, 300, 200);

        int count = coverImageRepository.save(coverImage);

        assertThat(count).isEqualTo(1);
    }
    
    @Test
    void 강의_ID로_커버_이미지를_조회할_수_있다() {
        CoverImage coverImage = new CoverImage(1L, 1_048_576L, ImageType.JPG, 300, 200);
        coverImageRepository.save(coverImage);

        CoverImage savedCoverImage = coverImageRepository.findBySessionId(1L);

        assertThat(savedCoverImage.getSessionId()).isEqualTo(1L);
        assertThat(savedCoverImage.getType()).isEqualTo(ImageType.JPG);
    }
}
