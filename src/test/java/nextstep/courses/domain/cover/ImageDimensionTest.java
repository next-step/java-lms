package nextstep.courses.domain.cover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageDimensionTest {


    @DisplayName("유효한 이미지 크기로 ImageDimension 객체를 생성한다.")
    @Test
    void createImageDimensionTest() {
        int width = 300;
        int height = 200;
        ImageDimension imageDimension = ImageDimension.of(width, height);
        assertThat(imageDimension.getWidth()).isEqualTo(width);
        assertThat(imageDimension.getHeight()).isEqualTo(height);
    }

    @DisplayName("이미지의 크기가 유효하지 않으면 예외가 발생한다.")
    @Test
    void throwExceptionWhenImageSizeInvalid() {
        assertThatThrownBy(() -> ImageDimension.of(250, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 크기가 유효하지 않습니다.");
    }

    @DisplayName("이미지의 비율이 유효하지 않으면 예외가 발생한다.")
    @Test
    void throwExceptionWhenImageRatioInvalid() {
        assertThatThrownBy(() -> ImageDimension.of(400, 300))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 비율이 유효하지 않습니다.");
    }
}

