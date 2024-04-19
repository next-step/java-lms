package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.ImageRepository;
import nextstep.sessions.domain.image.Capacity;
import nextstep.sessions.domain.image.Image;
import nextstep.sessions.domain.image.ImageSize;
import nextstep.sessions.domain.image.ImageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @DisplayName("이미지를 저장한다")
    @Test
    void save() {
        Image image = new Image(10, ImageType.JPEG, new ImageSize(300, 200));

        assertThat(imageRepository.save(image)).isEqualTo(1);
    }

    @DisplayName("이미지를 조회한다")
    @Test
    void findById() {
        Image image = new Image(2L, new Capacity(10), ImageType.JPEG, new ImageSize(300, 200));
        imageRepository.save(image);

        Optional<Image> savedImage = imageRepository.findById(2L);

        assertThat(savedImage).isNotEmpty();
    }

}
