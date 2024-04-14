package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("도메인 객체 ImageRate 테스트")
class ImageRateTest {
    @DisplayName("이미지 비율 검증")
    @Test
    void 이미지_비율_검증() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new ImageRate(300, 100))
            .withMessage("이미지 width, height 비율이 3:2를 만족하지 않습니다");
    }

    @DisplayName("이미지 최저 픽셀 검증")
    @Test
    void 이미지_최저_픽셀_검증() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new ImageRate(30, 20))
            .withMessage("이미지 width가 300px 보다 작습니다");
    }
}

