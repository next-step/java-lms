package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CoverImageTest {
    @Test
    void validateSizeTest() {
        assertThatThrownBy(() -> {
            new CoverImage(1024L * 1024L * 3, 300, 200, ImageType.GIF);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"299, 200", "300, 199"})
    void validatePixelTest(int width, int height) {
        assertThatThrownBy(() -> {
            new CoverImage(1, width, height, ImageType.GIF);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateRatioTest() {
        assertThatThrownBy(() -> {
            new CoverImage(1, 400, 400, ImageType.GIF);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
