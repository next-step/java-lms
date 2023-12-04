package nextstep.image;

import nextstep.image.domain.Image;
import nextstep.image.domain.ImageType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTest {

    @Test
    void 이미지_크기는_1MB_이하여야_한다() {
        assertThatThrownBy(() -> Image.of(Image.MIN_WIDTH, Image.MIN_HEIGHT, ImageType.JPG, 1025))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 크기는 1MB 이하여야 한다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"150, 300", "300, 150"}, delimiter = ',')
    void 이미지_width는_300픽셀_height는_200픽셀_이상이어야_한다(int width, int height) {
        assertThatThrownBy(() -> Image.of(width, height, ImageType.JPG, Image.MAXIMUM_SIZE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 한다.");

    }
}
