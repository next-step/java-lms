package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Image;
import nextstep.courses.domain.session.ImageRepository;
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
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Image image = Image.of("JPG", 300L, 200L, 1024);
        int count = imageRepository.save(image);
        assertThat(count).isEqualTo(1);
        Image saveImage = imageRepository.findById(1L);
        assertThat(saveImage.type()).isEqualTo(image.type());
        LOGGER.debug("Image: {}", saveImage);
    }
}
