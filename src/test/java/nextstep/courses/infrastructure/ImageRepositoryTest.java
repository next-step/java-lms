package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.*;
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

    @DisplayName("이미지 저장 테스트")
    @Test
    void 이미지_저장_테스트() {
        ImageName imageName = new ImageName("image.png");
        ImagePixel imagePixel = new ImagePixel(300, 200);
        ImageSize imageSize = new ImageSize(1000);
        Image image = new Image(imageName, imageSize, imagePixel,null);
        int result = imageRepository.save(image);
        assertThat(result).isOne();
    }

    @DisplayName("id로 이미지 조회 테스트")
    @Test
    void id로_조회_테스트() {
        ImageName imageName = new ImageName("image.png");
        ImagePixel imagePixel = new ImagePixel(300, 200);
        ImageSize imageSize = new ImageSize(1000);
        Image image = new Image(imageName, imageSize, imagePixel,null);
        imageRepository.save(image);
        Image result = imageRepository.findById(1L);
        assertThat(result.name()).isEqualTo(image.name());
    }

    @DisplayName("session id로 이미지 조회 테스트")
    @Test
    void session_id로_조회_테스트() {
        ImageName imageName = new ImageName("image.png");
        ImagePixel imagePixel = new ImagePixel(300, 200);
        ImageSize imageSize = new ImageSize(1000);
        Image image = new Image(imageName, imageSize, imagePixel,1L);
        imageRepository.save(image);
        Image results = imageRepository.findBySessionId(1L);
        assertThat(results.name()).isEqualTo(image.name());
    }
}
