package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.entity.ImageEntity;
import nextstep.courses.fixture.builder.ImageBuilder;
import nextstep.courses.infrastructure.impl.JdbcImageEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class ImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageEntityRepository(jdbcTemplate);
    }

    @Test
    void Image는_저장되어야_한다() {
        Image image = ImageBuilder.anImage()
            .withSize(new ImageSize(1))
            .withType(ImageType.JPG)
            .withWidth(new ImageWidth(300))
            .withHeight(new ImageHeight(200))
            .build();

        int saveCount = imageRepository.save(ImageEntity.from(image, 1L));

        assertThat(saveCount).isEqualTo(1);
    }

    @Test
    void Image는_조회되어야_한다() {
        ImageEntity imageEntity = imageRepository.findById(1L).get();
        assertThat(imageEntity)
            .extracting("id", "size", "type", "width", "height", "sessionId", "createdAt")
            .containsExactly(1L, 1, "JPEG", 300, 200, 1L, LocalDateTime.parse("2024-01-01T00:00:00"));
    }
}
