package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("도메인 객체 ImageSize 테스트")
class ImageSizeTest {
    @DisplayName("이미지 1MB 초과 검증")
    @Test
    void 이미지_용량_검증() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new ImageSize(2))
            .withMessage("이미지 사이즈가 1MB를 초과했습니다: 2MB");
    }
}
