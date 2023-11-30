package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.Images;
import nextstep.courses.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;

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
    void create() {
        Image image = new Image(1, "JPG", 300, 200);
        int count = imageRepository.save(image, 2L);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void read() {
        Image savedImage = imageRepository.findById(2L);
        Image image = new Image(2L, 0.3, "GIF", 300, 200, LocalDateTime.of(2023, 11, 11, 12, 12, 12), null);
        assertThat(savedImage).isEqualTo(image);
        LOGGER.debug("Image: {}", savedImage);
    }

    @Test
    void read_all() {
        Images savedImages = imageRepository.findAllBySessionId(3L);
        Image image1 = new Image(3L, 0.3, "GIF", 300, 200, LocalDateTime.of(2024, 11, 11, 12, 12, 12), null);
        Image image2 = new Image(4L, 1.0, "JPG", 300, 200, LocalDateTime.of(2025, 11, 11, 12, 12, 12), null);
        assertThat(savedImages).isEqualTo(new Images(Arrays.asList(image1, image2)));
    }

}
