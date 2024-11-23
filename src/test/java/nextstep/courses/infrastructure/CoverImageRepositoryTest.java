package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    private CoverImage coverImage;
    private List<CoverImage> coverImages;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        coverImages = new ArrayList<>();

        coverImage = CoverImage.of("nextstep", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
        coverImages.add(coverImage);
    }

    @DisplayName("커버이미지를 저장하고 조회할 수 있다.")
    @Test
    void saveAndFindBySessionId() {
        Long sessionId = 1L;
        coverImageRepository.save(coverImages, sessionId);
        List<CoverImage> foundCoverImages = coverImageRepository.findBySessionId(sessionId);

        assertThat(foundCoverImages).isNotEmpty();
        assertThat(foundCoverImages).hasSize(1);

        CoverImage retrievedCoverImage = foundCoverImages.get(0);

        assertThat(retrievedCoverImage)
                .usingRecursiveComparison()
                .isEqualTo(coverImage);
    }
}