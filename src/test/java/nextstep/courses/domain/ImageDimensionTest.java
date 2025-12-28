package nextstep.courses.domain;

import nextstep.courses.domain.session.cover.ImageDimension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageDimensionTest {

    @Test
    @DisplayName("width가 300px 미만이면 예외가 발생한다")
    void create_width_exception() {
        assertThatThrownBy(() -> new ImageDimension(299, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("height가 200px 미만이면 예외가 발생한다")
    void create_height_exception() {
        assertThatThrownBy(() -> new ImageDimension(300, 199))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("width와 height는 3:2 비율이 아니면 예외가 발생한다")
    void create_width_height_exception() {
        assertThatThrownBy(() -> new ImageDimension(300, 300))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("width_height_정상생성")
    void create_success() {
        assertThatNoException()
                .isThrownBy(() -> new ImageDimension(300, 200));

        assertThatNoException()
                .isThrownBy(() -> new ImageDimension(600, 400));
    }
}