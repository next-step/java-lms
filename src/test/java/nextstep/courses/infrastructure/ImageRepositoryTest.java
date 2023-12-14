package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.*;
import nextstep.courses.domain.session.Images;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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

    public static Image createImage(String name, int volume, int width, int height) {
        ImageName imageName = new ImageName(name);
        ImagePixel imagePixel = new ImagePixel(width, height);
        ImageSize imageSize = new ImageSize(volume);
        return new Image(imageName, imageSize, imagePixel,1L);
    }

    @DisplayName("이미지 저장 테스트")
    @Test
    void 이미지_저장_테스트() {
        Image image = createImage("image.png", 1000, 300, 200);
        int result = imageRepository.save(image);
        assertThat(result).isOne();
    }

    @DisplayName("id로 이미지 조회 테스트")
    @Test
    void id로_조회_테스트() {
        Image image = createImage("image.png", 1000, 300, 200);
        imageRepository.save(image);
        Image result = imageRepository.findById(1L);
        assertThat(result.name()).isEqualTo(image.name());
    }

    @DisplayName("session id로 이미지 조회 테스트")
    @Test
    void session_id로_조회_테스트() {
        Image image = createImage("image.png", 1000, 300, 200);
        imageRepository.save(image);
        Images results = imageRepository.findBySessionId(1L);
        assertThat(results.size()).isEqualTo(1);
    }

    @DisplayName("이미지 저장 여러개 테스트")
    @Test
    void 이미지_저장_여러개_테스트() {
        Image image = createImage("image.png", 1000, 300, 200);
        Image image2 = createImage("image2.png", 1000, 300, 200);
        Images images = new Images(List.of(image, image2));
        imageRepository.saveAll(images);
        Images results = imageRepository.findBySessionId(1L);
        assertThat(results.size()).isEqualTo(2);
    }
}
