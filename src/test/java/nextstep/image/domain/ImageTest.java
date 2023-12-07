package nextstep.image.domain;

import nextstep.imgae.domain.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImageTest {

    @DisplayName("이미지 크기가 1MB를 초과하면 에러 발생")
    @Test
    void 이미지_크기_초과() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Image(1L, 1025, "jpg", 300, 200))
                .withMessage("이미지 크기가 1MB를 초과했습니다.");
    }

    @DisplayName("최소 너비 미만이면 에러 발생")
    @Test
    void 최소_너비_미만() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Image(1L, 1024, "jpg", 200, 200))
                .withMessage("이미지 너비가 300픽셀 미만입니다.");
    }

    @DisplayName("최소 높이 미만이면 에러 발생")
    @Test
    void 최소_높이_미만() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Image(1L, 1024, "jpg", 300, 100))
                .withMessage("이미지 높이가 200픽셀 미만입니다.");
    }

    @DisplayName("너비와 높이 비율이 3:2가 아니면 에러 발생")
    @Test
    void 비율_안맞음() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Image(1L, 1024, "jpg", 450, 400))
                .withMessage("이미지 너비/높이 비율이 3:2가 아닙니다.");
    }
}
