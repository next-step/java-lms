package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    private CoverImage coverImage;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);

        coverImage = CoverImage.of("nextstep", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
    }

    @DisplayName("커버이미지를 저장하고 조회할 수 있다.")
    @Test
    void saveAndFindBySessionId() {
        Long sessionId = 1L;
        coverImageRepository.save(coverImage, sessionId);
        Optional<CoverImage> foundCoverImage = coverImageRepository.findBySessionId(sessionId);

        assertThat(foundCoverImage).isPresent();
        CoverImage retrievedCoverImage = foundCoverImage.get();

        assertAll(
                () -> assertThat(retrievedCoverImage.getFileName()).isEqualTo(coverImage.getFileName()),
                () -> assertThat(retrievedCoverImage.getImageSize()).isEqualTo(coverImage.getImageSize()),
                () -> assertThat(retrievedCoverImage.getExtension()).isEqualTo(coverImage.getExtension()),
                () -> assertThat(retrievedCoverImage.getWidth()).isEqualTo(coverImage.getWidth()),
                () -> assertThat(retrievedCoverImage.getHeight()).isEqualTo(coverImage.getHeight())
        );
    }
}