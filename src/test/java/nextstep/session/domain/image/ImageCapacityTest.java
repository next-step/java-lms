package nextstep.session.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageCapacityTest {

    @DisplayName("이미지가 1MB를 초과하면 예외가 발생한다.")
    @Test
    void createImageOverCapacityThrowExceptionTest() {
        //when, then
        assertThatThrownBy(() -> new ImageCapacity(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 사이즈는 1MB를 초과하면 안됩니다.");
    }
}
