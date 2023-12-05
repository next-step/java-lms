package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImagePixelTest {
    @ParameterizedTest
    @CsvSource({ "299, 202", "300, 199"})
    void 이미지는_최소해상도_미만일떄(int width, int height) {
        assertThatThrownBy(() -> {
            new ImagePixel(width, height);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지는_해상도가_3x2비율이_아닐때() {
        assertThatThrownBy(() -> {
            new ImagePixel(400, 400);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}