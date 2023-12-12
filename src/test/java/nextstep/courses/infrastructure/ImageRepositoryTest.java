package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.image.Image;
import nextstep.courses.domain.course.image.ImageRepository;
import nextstep.courses.domain.course.image.ImageType;
import nextstep.qna.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

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
    void save_success() {
        Image image = new Image(1024, ImageType.JPG, 300, 200, 1L, LocalDateTime.now());
        int count = imageRepository.save(image);
        Image savedImage = imageRepository.findById(1L).orElseThrow(NotFoundException::new);
        assertThat(image.getImageSize()).isEqualTo(savedImage.getImageSize());
        assertThat(image.getImageType()).isEqualTo(savedImage.getImageType());
        assertThat(image.getImageWidth()).isEqualTo(savedImage.getImageWidth());
        assertThat(image.getImageHeight()).isEqualTo(savedImage.getImageHeight());
        LOGGER.debug("Image: {}", savedImage);
    }
}
