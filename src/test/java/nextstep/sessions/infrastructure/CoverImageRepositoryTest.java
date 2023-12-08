package nextstep.sessions.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import nextstep.sessions.domain.data.type.ImageType;
import nextstep.sessions.domain.data.vo.CoverImage;
import nextstep.sessions.repository.CoverImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void 커버_이미지_여러개_생성_및_조회() {
        List<CoverImage> coverImages = List.of(
            new CoverImage("image.png", 102400, 300, 200),
            new CoverImage("image.gif", 102400, 300, 200)
        );

        int count = coverImageRepository.saveAll(1, coverImages);
        assertThat(count).isEqualTo(2);

        List<CoverImage> savedCoverImages = coverImageRepository.findById(1);
        List<ImageType> imageTypes = savedCoverImages.stream()
            .map(CoverImage::imageType)
            .collect(Collectors.toList());
        assertThat(imageTypes).contains(ImageType.PNG);
        assertThat(imageTypes).contains(ImageType.GIF);
    }
}
