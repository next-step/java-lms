package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFileException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageDimensionTest {
    @ParameterizedTest
    @CsvSource(value = {"90,60", "300,300"})
    void 이미지_너비와_높이와_비율에_제한이_있다(int width, int height) {
        assertThatThrownBy(() -> new ImageDimension(90, 60))
                .isInstanceOf(InvalidImageFileException.class);

        assertThatThrownBy(() -> new ImageDimension(300, 300))
                .isInstanceOf(InvalidImageFileException.class);
    }
}