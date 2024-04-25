package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.CoverImageRepository;
import nextstep.courses.domain.session.image.CoverImage;
import nextstep.courses.domain.session.image.CoverImages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
class CoverImageRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("커버 이미지 저장 후 조회 테스트")
    void testCoverImages_saveAndFindBySessionId_ShouldReturnCoverImages() {
        // given
        CoverImages coverImages = getCoverImages();
        Long sessionId = 1L;

        // when
        int count = coverImageRepository.save(sessionId, coverImages);

        // then
        assertEquals(count, 2);

        // when
        List<CoverImage> pluralCoverImages = coverImageRepository.findBySessionId(sessionId);

        // then
        assertEquals(pluralCoverImages.size(), 2);

        CoverImage firstImage = pluralCoverImages.get(0);
        CoverImage secondImage = pluralCoverImages.get(1);

        // then
        assertAll(
                "The first cover image is correctly loaded",
                () -> assertEquals(firstImage.getId(), 1L),
                () -> assertEquals(firstImage.getImageType(), "jpg"),
                () -> assertEquals(firstImage.getImageFileSize(), 1024),
                () -> assertEquals(firstImage.getImageSizeWidth(), 300),
                () -> assertEquals(firstImage.getImageSizeHeight(), 200)
        );

        assertAll(
                "The second cover image is correctly loaded",
                () -> assertEquals(secondImage.getId(), 2L),
                () -> assertEquals(secondImage.getImageType(), "gif"),
                () -> assertEquals(firstImage.getImageFileSize(), 1024),
                () -> assertEquals(firstImage.getImageSizeWidth(), 300),
                () -> assertEquals(firstImage.getImageSizeHeight(), 200)
        );
    }

    private CoverImages getCoverImages() {
        List<CoverImage> coverImages = List.of(
                (CoverImage.of("jpg", 1024, 300, 200)),
                (CoverImage.of("gif", 1024, 300, 200))
        );

        return CoverImages.of(coverImages);
    }

}
