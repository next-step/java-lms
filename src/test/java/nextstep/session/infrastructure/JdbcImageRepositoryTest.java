package nextstep.session.infrastructure;

import nextstep.session.domain.image.Image;
import nextstep.session.domain.image.ImageRepository;
import nextstep.support.TestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcImageRepositoryTest extends TestSupport {

    @DisplayName("이미지를 저장 후 조회한다.")
    @Test
    void saveImageTest() {
        ImageRepository imageRepository = new JdbcImageRepository(jdbcTemplate);
        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        imageRepository.save(image);

        assertThat(imageRepository.findById(image.getId()))
                .extracting("id", "name", "size.width", "size.height", "capacity.capacity", "sessionId")
                .containsExactly(1L, "테스트이미지.jpg", 300, 200, 1, 1L);
    }
}
