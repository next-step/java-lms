package nextstep.courses.domain;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTest {
    @Test
    @Order(10)
    void 유효한_이미지일_경우_객체생성() {
        assertThat(new NsImage(900_000, "image/jpeg", 600, 400))
                .isInstanceOf(NsImage.class);
    }

    @Test
    @Order(20)
    void 파일크기가_1MB를_초과_생성실패() {
        assertThatThrownBy(() -> new NsImage(1_000_001, "image/jpeg", 600, 400))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("파일 크기는 1MB를 초과할 수 없습니다.");
    }

    @Test
    @Order(30)
    void 허용되지_않은_파일타입_생성실패() {
        assertThatThrownBy(() -> new NsImage(900_000, "image/webp", 600, 400))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 이미지 파일이 아닙니다.");
    }

    @Test
    @Order(40)
    void 너비가_300px_미만_생성실패() {
        assertThatThrownBy(() -> new NsImage(900_000, "image/png", 250, 400))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 이미지 사이즈가 아닙니다.");
    }

    @Test
    @Order(50)
    void 높이가_200px_미만_생성실패() {
        assertThatThrownBy(() -> new NsImage(900_000, "image/png", 600, 150))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효한 이미지 사이즈가 아닙니다.");
    }

    @Test
    @Order(60)
    void 이미지_비율이_3대2가아님_생성실패() {
        assertThatThrownBy(() -> new NsImage(900_000, "image/png", 600, 500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지 width와 height의 비율은 3:2여야 합니다.");
    }
}
