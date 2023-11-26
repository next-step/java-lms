package nextstep.courses.domian;

import nextstep.courses.InvalidImageSizeException;
import nextstep.courses.domain.CoverImagePixel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImagePixelTest {

    @Test
    @DisplayName("조건에 맞는 가로와 세로 픽셀일 경우 CoverImagePixel은 정상적으로 생성된다.")
    void createCoverImagePixel() {
        CoverImagePixel coverImagePixel = new CoverImagePixel(300, 200);

        assertThat(coverImagePixel).isEqualTo(new CoverImagePixel(300, 200));
    }

    @Test
    @DisplayName("가로 픽셀이 300미만 이라면 오류가 발생한다.")
    void createCoverImagePixel_invalid_width() {
        assertThatThrownBy(() -> new CoverImagePixel(100, 200))
                .isInstanceOf(InvalidImageSizeException.class)
                .hasMessage("가로 픽셀은 최소 300픽셀 이상이어야 합니다.");
    }

    @Test
    @DisplayName("세로 픽셀이 200미만 이라면 오류가 발생한다.")
    void createCoverImagePixel_invalid_height() {
        assertThatThrownBy(() -> new CoverImagePixel(300, 100))
                .isInstanceOf(InvalidImageSizeException.class)
                .hasMessage("세로 픽셀은 최소 200픽셀 이상이어야 합니다.");
    }

    @Test
    @DisplayName("가로와 세로의 비율이 3:2가 아니라면 오류가 발생한다.")
    void createCoverImagePixel_invalid_ratio() {
        assertThatThrownBy(() -> new CoverImagePixel(400, 200))
                .isInstanceOf(InvalidImageSizeException.class)
                .hasMessage("가로와 세로의 비율은 3:2 여야 합니다.");
    }
}
