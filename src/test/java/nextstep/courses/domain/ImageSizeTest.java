package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ImageSizeTest {

    @ParameterizedTest
    @ValueSource(ints = {299, 0})
    void 가로사이즈_예외(int width) {
        assertThatThrownBy(() -> new ImageSize(width, 100))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {199, 0})
    void 세로사이즈_예외(int height) {
        assertThatThrownBy(() -> new ImageSize(300, height))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비율_예외() {
        int width = 301;
        int height = 199;
        assertThatThrownBy(() -> new ImageSize(width, height))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
