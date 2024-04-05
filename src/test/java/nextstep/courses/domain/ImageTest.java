package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageTest {

    @Test
    void capacityException() {
        Assertions.assertThatThrownBy(() -> new Image(5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지크기는 1MB를 초과할 수 업다");
    }


}
