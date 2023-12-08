package nextstep.image.domain;

import static nextstep.image.domain.ImageSize.HEIGHT_VALIATION_EXCEPTION;
import static nextstep.image.domain.ImageSize.RATIO_VALIDATION_EXCEPTION;
import static nextstep.image.domain.ImageSize.WIDTH_VALIDATION_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.image.exception.HeightValidationException;
import nextstep.image.exception.RatioValidationException;
import nextstep.image.exception.WidthValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageSizeTest {

    @Test
    @DisplayName("이미지 width가 300 픽셀 이상인지 검증한다.")
    void width_exception() {
        // given
        int width = 299;
        int height = 200;

        // when // then
        assertThatThrownBy(() -> new ImageSize(width, height))
                .isExactlyInstanceOf(WidthValidationException.class)
                .hasMessage(WIDTH_VALIDATION_EXCEPTION);
    }

    @Test
    @DisplayName("이미지의 height는 200픽셀 이상인지 검증한다.")
    void height_exception() {
        // given
        int width = 300;
        int height = 199;

        // when // then
        assertThatThrownBy(() -> new ImageSize(width, height))
                .isExactlyInstanceOf(HeightValidationException.class)
                .hasMessage(HEIGHT_VALIATION_EXCEPTION);
    }

    @Test
    @DisplayName("width와 height의 비율은 3:2 인지 검증한다.")
    void validate_ratio() {
        // given
        int width = 300;
        int height = 201;

        // when // then
        assertThatThrownBy(() -> new ImageSize(width, height))
                .isExactlyInstanceOf(RatioValidationException.class)
                .hasMessage(RATIO_VALIDATION_EXCEPTION);
    }
}
