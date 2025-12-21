package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ImageDimensionTest {

    @Test
    void 이미지의_width가_기준보다_작다면_예외가_발생한다() {
        assertThatThrownBy(() -> new ImageDimension(200, 200))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("이미지 너비는 300픽셀 이상이어야 합니다.");
    }

    @Test
    void 이미지의_height가_기준보다_작다면_예외가_발생한다() {
        assertThatThrownBy(() -> new ImageDimension(300, 150))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("이미지 높이는 200픽셀 이상이어야 합니다");
    }

    @Test
    void width와_height의_비율이_기준에_맞지_않을_때_예외가_발생한다() {
        assertThatThrownBy(() -> new ImageDimension(500, 400))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("이미지 비율은 3:2여야 합니다.");
    }
}
