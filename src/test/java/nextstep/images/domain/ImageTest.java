package nextstep.images.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ImageTest {

    @ParameterizedTest
    @CsvSource(value = {"0", "1048577"})
    void 이미지_사이즈_크기_exception_테스트(int size) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(1L, ImageType.GIF, size, 1, 1));
    }

    @Test
    void 이미지_타입_exception_테스트() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(1L, null, 0, 1, 1));
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 200", "200, 200", "500, 400"})
    void 이미지_가로_세로_exception_테스트(int width, int height) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(1L, ImageType.GIF, 1024 * 1024 - 1, width, height));
    }
}
