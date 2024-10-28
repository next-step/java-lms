package nextstep.session.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageSizeTest {

    @DisplayName("이미지의 너비 높이가 3:2 비율이 아니라면 예외가 발생한다.")
    @Test
    void createImageRateThrowExceptionTest() {
        //when, then
        assertThatThrownBy(() -> new ImageSize(200, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 너비 높이가 3:2 비율이여야 합니다.");
    }
}
