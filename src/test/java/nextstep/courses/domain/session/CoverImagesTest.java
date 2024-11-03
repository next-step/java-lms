package nextstep.courses.domain.session;

import nextstep.courses.domain.CoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

public class CoverImagesTest {

    private CoverImage coverImage1;
    private CoverImage coverImage2;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        createdAt = LocalDateTime.of(2024, 10, 26, 10, 0);
        updatedAt = LocalDateTime.of(2024, 11, 26, 10, 0);
        coverImage1 = new CoverImage(1L, 1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200, createdAt, updatedAt);
        coverImage2 = new CoverImage(2L, 1L, SIZE_512, IMAGE_TYPE_TEXT_JPG, WIDTH_450, HEIGHT_300, createdAt, updatedAt);
    }

    @Test
    void create() {
        CoverImages actual = new CoverImages(List.of(coverImage1, coverImage2));
        CoverImages expected = new CoverImages(coverImage1, coverImage2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void add() {
        CoverImages actual = new CoverImages(coverImage1);
        actual.add(coverImage2);
        CoverImages expected = new CoverImages(coverImage1, coverImage2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void size() {
        CoverImages coverImages = new CoverImages(coverImage1);
        coverImages.add(coverImage2);
        int actual = coverImages.size();

        assertThat(actual).isEqualTo(2);
    }

    @Test
    void getter() {
        CoverImages coverImages = new CoverImages(List.of(coverImage1, coverImage2));
        List<CoverImage> actual = coverImages.getCoverImages();

        assertThat(actual).isEqualTo(List.of(coverImage1, coverImage2));
    }
}
