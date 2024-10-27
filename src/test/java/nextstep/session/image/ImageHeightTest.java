package nextstep.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageHeightTest {

    @DisplayName("이미지의 높이가 200px을 초과하면 예외가 발생한다.")
    @Test
    void createImageOverHeightThrowExceptionTest() {

        assertThatThrownBy(() -> new ImageHeight(400))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 높이가 200px을 초과하면 안됩니다.");
    }
}
