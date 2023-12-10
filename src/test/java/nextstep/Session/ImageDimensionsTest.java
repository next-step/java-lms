package nextstep.Session;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ImageDimensionsTest {

    @ParameterizedTest
    @CsvSource({
        "299, 200",
        "300, 199",
        "450, 200"
    })
    @DisplayName("유효하지 않은 너비와 높이를 입력하면 예외를 반환한다.")
    void testInvalidImageDimensions(int width, int height) {
        assertThatIllegalArgumentException().isThrownBy(() -> new ImageDimensions(width, height));
    }

}