package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTest {

    @Test
    @DisplayName("이미지는 타입과 넓이와 높이를 가질 수 있다.")
    void 이미지는_타입과_넓이와_높이를_가질_수_있다() {
        Image image = new Image("png", 300, 200);
        assertThat(image).hasFieldOrProperty("type");
        assertThat(image).hasFieldOrProperty("width");
        assertThat(image).hasFieldOrProperty("height");
    }

    @Test
    @DisplayName("이미지 크기는 1MB 초과일 경우 예외가 발생한다.")
    void 이미지_크기는_1MB_초과할_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Image("png", 3000, 2000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.")
    void 허용된_이미지가_아닐_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Image("존윅", 300, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지의 width는 300픽셀 이상이어야 한다.")
    void 이미지의_width가_300px_미만일_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Image("png", 299, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지의 height는 200픽셀 이상이어야 한다.")
    void 이미지의_height가_200px_미만일_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Image("jpg", 300, 199))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지의 width와 height의 비율은 3:2여야 한다.")
    void 이미지의_width와_height의_비율이_3_대_2_아닐_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Image("jpg", 500, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
