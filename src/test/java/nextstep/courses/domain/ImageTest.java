package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ImageTest {

    @Test
    void 생성(){
        Image test02 = new Image("test_02.png");
    }

    @Test
    void 생성_비율_예외발생() {
        assertThatThrownBy(() -> new Image("test_01.jpeg"))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void 생성_확장자_예외발생() {
        assertThatThrownBy(() -> new Image("test_04.webp"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("파일 확장자가 올바르지 않습니다. 해당 파일 확장자는 webp 입니다.");
    }
}
