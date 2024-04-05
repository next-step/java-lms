package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.image.Type.GIF;

public class ImageTest {

    @Test
    void capacityException() {
        Assertions.assertThatThrownBy(() -> new Image(5, GIF))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지크기는 1MB를 초과할 수 업다");
    }


}
