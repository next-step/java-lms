package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ImagePixelTest {
    @DisplayName("이미지 크기 예외 테스트")
    @ParameterizedTest(name = "Width = {0}, Height = {1}")
    @CsvSource(value = {
            "100 : 200",
            "300 : 100",
            "400 : 500",
    }, delimiter = ':')
    void image_pixel_fail_test(int width, int height) {
        assertThatIllegalArgumentException().isThrownBy((() ->
                new ImagePixel(width, height)));
    }
}
