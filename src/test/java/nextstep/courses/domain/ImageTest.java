package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.image.Type.GIF;

public class ImageTest {

    @DisplayName("이미지 크기가 1MB를 초과하면 예외를 반환한다")
    @Test
    void capacityException() {
        Assertions.assertThatThrownBy(() -> new Image(0, GIF, 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지크기는 1MB를 초과할 수 없다");
    }

    @DisplayName("이미지 width가 300픽셀미만이면 예외를 반환한다")
    @Test
    void widthException() {
        Assertions.assertThatThrownBy(() -> new Image(5, GIF, 200, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 width는 300픽셀이상이어야 한다");
    }


    @DisplayName("이미지 height가 200픽셀미만이면 예외를 반환한다")
    @Test
    void heightException() {
        Assertions.assertThatThrownBy(() -> new Image(5, GIF, 300, 100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 height는 200픽셀이상이어야 한다");
    }

    @DisplayName("이미지 비율이 3:2가 아니면 예외를 반환한다")
    @Test
    void ratioException() {
        Assertions.assertThatThrownBy(() -> new Image(5, GIF, 600, 300))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("width와 height의 비율은 3:2여야 한다");
    }

}
