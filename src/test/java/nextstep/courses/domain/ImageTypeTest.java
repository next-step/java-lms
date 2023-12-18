package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ImageTypeTest {
    @Test
    @DisplayName("이미지 타입이 gif, jpg, jpeg, png, svg 아니면 예외가 던져진다")
    void image_type_exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ImageType.of("mp4");
        });
    }
}
