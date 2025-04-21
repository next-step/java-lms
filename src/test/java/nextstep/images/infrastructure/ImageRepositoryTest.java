package nextstep.images.infrastructure;

import nextstep.images.domain.Image;
import nextstep.images.domain.ImageRepository;
import nextstep.images.domain.ImageType;
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
        Image image = new Image.Builder()
                .id(1L)
                .type(ImageType.JPEG)
                .size(500.0)
                .dimension(300.0, 200.0)
                .build();
        int count = imageRepository.save(image);
        assertThat(count).isEqualTo(1);
        Image savedImage = imageRepository.findById(1L);
        assertThat(image.getType()).isEqualTo(savedImage.getType());
        LOGGER.debug("Image: {}", savedImage);
    }
}
