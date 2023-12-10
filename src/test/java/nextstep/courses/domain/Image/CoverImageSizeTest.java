package nextstep.courses.domain.Image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageSizeTest {

    @DisplayName("이미지의 사이즈가 1MB를 넘으면 예외를 던진다")
    @Test
    void sizeException() {
        assertThatThrownBy(() -> new CoverImageSize(1024 * 1024 + 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 사이즈는 1MB를 넘을 수 없습니다");
    }
}