package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImagePixel;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ImageRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        autoincrementReset();
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        final CoverImage coverImage = new CoverImage(1024L, new ImagePixel(300, 200), ImageType.GIF);
        int count = imageRepository.save(coverImage);
        assertThat(count).isEqualTo(1);
        CoverImage savedImage = imageRepository.findById(1L).get();
        assertThat(savedImage.size()).isEqualTo(1024L);
        assertThat(savedImage.imagePixel().width()).isEqualTo(300);
        assertThat(savedImage.imagePixel().height()).isEqualTo(200);
    }

    private void autoincrementReset() {
        jdbcTemplate.execute("ALTER TABLE course ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE image ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE session ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE enrollment ALTER COLUMN id RESTART WITH 1");
    }
}
