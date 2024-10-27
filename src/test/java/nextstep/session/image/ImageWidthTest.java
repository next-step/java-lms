package nextstep.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageWidthTest {

    @DisplayName("이미지의 너비가 300px을 초과하면 예외가 발생한다.")
    @Test
    void createImageOverWidthThrowExceptionTest() {

        assertThatThrownBy(() -> new ImageWidth(600))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 너비가 300px을 초과하면 안됩니다.");
    }
}
