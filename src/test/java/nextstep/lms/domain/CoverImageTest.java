package nextstep.lms.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CoverImageTest {
    @DisplayName("파일 확장자가 지원하지 않는 확장자라면 예외 발생")
    @Test
    void 확장자_확인() {
        assertThatThrownBy(() -> new CoverImage("next.step", 0.44, 300, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파일용량이 최대 크기보다 크다면 예외 발생")
    @Test
    void 용량_확인() {
        assertThatThrownBy(() -> new CoverImage("next.png", 1.2, 300, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파일 너비가 최소 너비보다 작다면 예외발생")
    @Test
    void 너비_확인() {
        assertThatThrownBy(() -> new CoverImage("next.png", 0.44, 250, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파일 높이가 최소 높이보다 작다면 예외발생")
    @Test
    void 높이_확인() {
        assertThatThrownBy(() -> new CoverImage("next.png", 0.44, 300, 150))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파일 비율이 지원하지 않는 비율이라면 예외발생")
    @Test
    void 너비_높이_비율_확인() {
        assertThatThrownBy(() -> new CoverImage("next.png", 0.44, 400, 150))
                .isInstanceOf(IllegalArgumentException.class);
    }
}