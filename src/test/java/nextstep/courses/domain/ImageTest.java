package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ImageTest {

    @Test
    void 생성_비율_예외발생() {
        assertThatThrownBy(() -> new Image("test_01.jpeg", 350, 200, 1_000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_확장자_예외발생() {
        assertThatThrownBy(() -> new Image("test_04.webp", 300, 200, 1_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일 확장자가 올바르지 않습니다. 해당 파일 확장자는 webp 입니다.");
    }
}
