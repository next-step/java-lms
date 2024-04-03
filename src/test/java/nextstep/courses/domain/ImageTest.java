package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.ImageType.JPG;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTest {

    @Test
    @DisplayName("1MB가 넘는 이미지는 생성할 수 없다.")
    void checkSize(){
        assertThatThrownBy(() -> new Image(3 * 1024 * 1024, JPG, 300, 200)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("width와 height의 비율은 3:2여야 한다")
    void checkWidthHeight(){
        assertThatThrownBy(() -> new Image( 1024, JPG, 300, 250)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지 요구사항에 맞으면 생성된다")
    void create(){
        assertThatNoException().isThrownBy(() -> new Image(1024, JPG, 30, 20));
    }
}
