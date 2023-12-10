package nextstep.courses.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ImageTest {

    @Test
    void 이미지_크기는_1MB_이하여야_한다() {
        // given
        int size = 2;

        // when, then
        assertThatThrownBy(() -> new Image(size)).isInstanceOf(IllegalArgumentException.class);
    }
}
