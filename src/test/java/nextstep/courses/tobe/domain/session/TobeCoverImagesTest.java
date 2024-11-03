package nextstep.courses.tobe.domain.session;

import nextstep.courses.tobe.domain.TobeCoverImage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.TobeCoverImageTest.*;
import static org.assertj.core.api.Assertions.*;

public class TobeCoverImagesTest {

    private TobeCoverImage coverImage1;
    private TobeCoverImage coverImage2;

    @BeforeEach
    void setUp() {
        coverImage1 = new TobeCoverImage(1L, 1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200, START, START);
        coverImage2 = new TobeCoverImage(2L, 1L, SIZE_512, IMAGE_TYPE_TEXT_JPG, WIDTH_450, HEIGHT_300, START, START);
    }

    @Test
    void create() {
        TobeCoverImages actual = new TobeCoverImages(List.of(coverImage1, coverImage2));
        TobeCoverImages expected = new TobeCoverImages(coverImage1, coverImage2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void add() {
        TobeCoverImages actual = new TobeCoverImages(coverImage1);
        actual.add(coverImage2);
        TobeCoverImages expected = new TobeCoverImages(coverImage1, coverImage2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void size() {
        TobeCoverImages coverImages = new TobeCoverImages(coverImage1);
        coverImages.add(coverImage2);
        int actual = coverImages.size();

        assertThat(actual).isEqualTo(2);
    }

    @Test
    void getter() {
        TobeCoverImages coverImages = new TobeCoverImages(List.of(coverImage1, coverImage2));
        List<TobeCoverImage> actual = coverImages.getCoverImages();

        assertThat(actual).isEqualTo(List.of(coverImage1, coverImage2));
    }
}
