package nextstep.session.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageHeightTest {

    @DisplayName("이미지의 높이가 200px 미만이면 예외가 발생한다.")
    @Test
    void createImageOverHeightThrowExceptionTest() {
        //when, then
        assertThatThrownBy(() -> new ImageHeight(100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 높이가 200px 이상이여야 합니다.");
    }
}
