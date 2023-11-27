package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.repository.ImageRepository;
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
        Image image = new Image(1, "JPG", 300, 200);
        int count = imageRepository.save(image);
        assertThat(count).isEqualTo(1);
        Image savedImage = imageRepository.findById(1L);
        assertThat(image).isEqualTo(savedImage);
        LOGGER.debug("Image: {}", savedImage);
    }

}
