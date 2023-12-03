package nextstep.courses.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImagePixelTest {

    @DisplayName("이미지 사이즈를 생성한다")
    @Test
    void 사이즈를_생성한다() {
        // given
        int width = 300;
        int height = 200;
        // when
        ImagePixel imagePixel = new ImagePixel(width, height);
        // then
        assertThat(imagePixel.width()).isEqualTo(width);
    }

    @DisplayName("이미지 사이즈의 너비와 높이의 비율이 3:2가 아니면 예외가 발생한다")
    @Test
    void 이미지_사이즈_비율이다르면_예외처리한다() {
        // given
        int width = 400;
        int height = 200;
        // when
        // then
        assertThatThrownBy(() -> new ImagePixel(width, height))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이미지 사이즈 최소크기가 300X200이 아니면 예외가 발생한다")
    @Test
    void 최소크기를_만족하지_못하면_예외처리한다() {
        // given
        int width = 299;
        int height = 200;
        // when
        // then
        assertThatThrownBy(() -> new ImagePixel(width, height))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
