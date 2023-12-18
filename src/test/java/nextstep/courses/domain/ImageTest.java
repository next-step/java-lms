package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ImageTest {
    @Test
    @DisplayName("이미지의 크기 1MB 초과하면 예외가 던져진다")
    void image_size_exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(2, "jpg", 300, 200);
        });
    }

    @Test
    @DisplayName("이미지의 width는 300픽셀, height는 200픽셀이 아니면 예외가 던져진다")
    void image_width_and_height_exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(1, "jpg", 300, 199);
            new Image(1, "jpg", 299, 200);
        });
    }
}
