package nextstep.session.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResolutionTest {

    @DisplayName("가로길이가 최소 가로길이보다 짧다면, IllegalArgumentException을 던진다.")
    @Test
    void throwIllegalArgumentExceptionOfMinimumWidth() {
        // then
        Assertions.assertThatThrownBy(() -> new Resolution(Resolution.MINIMUM_WIDTH - 1, 400))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @DisplayName("세로길이가 최소 세로길이보다 짧다면, IllegalArgumentException을 던진다.")
    @Test
    void throwIllegalArgumentExceptionOfMinimumHeight() {
        // then
        Assertions.assertThatThrownBy(() -> new Resolution(500, Resolution.MINIMUM_HEIGHT - 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가로와 세로의 비율이 3:2가 아니라면, IllegalArgumentException을 던진다.")
    @Test
    void throwIllegalArgumentExceptionOfWidthHeightRate() {
        // then
        Assertions.assertThatThrownBy(() -> new Resolution(500, 500))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
