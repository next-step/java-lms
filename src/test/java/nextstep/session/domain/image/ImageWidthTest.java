package nextstep.session.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageWidthTest {

    @DisplayName("이미지의 너비가 300px 미만이면 예외가 발생한다.")
    @Test
    void createImageOverWidthThrowExceptionTest() {
        //when, then
        assertThatThrownBy(() -> new ImageWidth(200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 너비가 300px 이상이여야 합니다.");
    }
}
