package nextstep.courses.domain;

import nextstep.courses.domain.ImageDimension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageDimensionTest {

    @Test
    @DisplayName("이미지의 너비는 300픽셀 이상")
    void width_should_over_300() {
        assertThatThrownBy(() -> {
            new ImageDimension(299L, 200L);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지의 높이는 200픽셀 이상")
    void height_should_over_200() {
        assertThatThrownBy(() -> {
            new ImageDimension(300L, 199L);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("width와 height의 비율은 3:2여야 한다")
    void check_width_height_ratio() {
        assertThatThrownBy(() -> {
            new ImageDimension(600L, 401L);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            new ImageDimension(600L, 399L);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
