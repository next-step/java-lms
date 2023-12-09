package nextstep.courses.infrastructure.image;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class JdbcImageRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    void saveAndFindTest() {
        // given
        int width = 300;
        int height = 200;
        long size = 500L;
        CoverImage coverImage = new CoverImage(width, height, size, ImageType.GIF);
        // when
        long id = imageRepository.save(coverImage);
        CoverImage imageResult = imageRepository.findById(id).get();
        // then
        assertThat(imageResult.getWidth()).isEqualTo(width);
        assertThat(imageResult.getHeight()).isEqualTo(height);
        assertThat(imageResult.getSize()).isEqualTo(size);
    }
}
