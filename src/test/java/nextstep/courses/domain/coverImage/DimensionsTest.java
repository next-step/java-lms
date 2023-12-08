package nextstep.courses.domain.coverImage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DimensionsTest {

    @DisplayName("width 값이 특정 값보다 작으면 안됩니다.")
    @Test
    void underWidth() {
        // given
        int width = 299;
        // when
        // then
        Assertions.assertThatThrownBy(() -> new Dimensions(width, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적절한 이미지 사이즈가 아닙니다.");
    }

    @DisplayName("height 값이 특정 값보다 작으면 안됩니다.")
    @Test
    void underHeight() {
        // given
        int height = 199;
        // when
        // then
        Assertions.assertThatThrownBy(() -> new Dimensions(300, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적절한 이미지 사이즈가 아닙니다.");
    }

    @DisplayName("width : height 비율이 특정 비율이어야 합니다.")
    @Test
    void widthHeight() {
        // given
        int width = 320;
        int height = 200;
        // when
        // then
        Assertions.assertThatThrownBy(() -> new Dimensions(width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적절한 이미지 사이즈가 아닙니다.");
    }

    @DisplayName("적절한 사이즈를 객체를 반환합니다.")
    @Test
    void successSize() {
        // given
        int width = 300;
        int height = 200;
        // when
        Dimensions dimensions = new Dimensions(width, height);
        // then
        Assertions.assertThat(dimensions).isEqualTo(new Dimensions(300, 200));
    }
}