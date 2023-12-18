package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTest {
    @Test
    @DisplayName("이미지의 크기 1MB 초과하면 예외가 던져진다")
    void image_size_exception() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(2);
        });
    }
}
