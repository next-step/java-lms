package nextstep.image.domain;

import nextstep.imgae.domain.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImageTypeTest {
    @DisplayName("이미지타입 안맞으면 에러")
    @Test
    void 비율_안맞음() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> ImageType.matchType("html"))
                .withMessage("이미지 타입이 올바르지 않습니다.");
    }
}
