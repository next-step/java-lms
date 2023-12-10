package nextstep.courses.domain.Image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImgaePixelTest {

    @DisplayName("이미지의 너비 픽셀이 300보다 작을 경우 예의를 던진다")
    @Test
    void widthException() {
        int width = 299;
        int height = 200;

        assertThatThrownBy(() -> new CoverImgaePixel(width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("너비 픽셀은 300픽셀 이상이어야 합니다");
    }

    @DisplayName("이미지의 높이 픽셀이 200보다 작을 경우 예의를 던진다")
    @Test
    void heightException() {
        int width = 300;
        int height = 199;

        assertThatThrownBy(() -> new CoverImgaePixel(width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("높이 픽셀은 200픽셀 이상이어야 합니다");
    }

    @DisplayName("이미지의 너비와 높이 비율이 3:2가 아니면 예외를 던진다")
    @Test
    void ratioException() {
        int width = 600;
        int height = 500;

        assertThatThrownBy(() -> new CoverImgaePixel(width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("너비와 높이의 비율은 3:2이어야 합니다");
    }
}