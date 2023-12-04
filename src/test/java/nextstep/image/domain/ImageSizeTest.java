package nextstep.image.domain;

import static nextstep.image.domain.ImageSize.WIDTH_VALIDATION_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.image.exception.WidthValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageSizeTest {

    @Test
    @DisplayName("이미지 width가 300 픽셀 이상인지 검증한다.")
    void width_exception() {
        // given
        int width = 299;

        // when // then
        assertThatThrownBy(() -> new ImageSize(299))
                .isExactlyInstanceOf(WidthValidationException.class)
                .hasMessage(WIDTH_VALIDATION_EXCEPTION);
    }

}
