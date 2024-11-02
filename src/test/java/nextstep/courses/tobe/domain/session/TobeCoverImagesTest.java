package nextstep.courses.tobe.domain.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.session.DateRangeTest.START;
import static nextstep.courses.tobe.domain.session.TobeCoverImageTest.*;
import static org.assertj.core.api.Assertions.*;

public class TobeCoverImagesTest {

    private TobeCoverImage coverImage1;
    private TobeCoverImage coverImage2;

    @BeforeEach
    void setUp() {
        coverImage1 = new TobeCoverImage(1L, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200, 1L, START);
        coverImage2 = new TobeCoverImage(2L, SIZE_512, IMAGE_TYPE_TEXT_JPG, WIDTH_450, HEIGHT_300, 1L, START);
    }

    @Test
    void create() {
        TobeCoverImages actual = new TobeCoverImages(List.of(coverImage1, coverImage2));
        TobeCoverImages expected = new TobeCoverImages(coverImage1, coverImage2);

        assertThat(actual).isEqualTo(expected);
    }
}
