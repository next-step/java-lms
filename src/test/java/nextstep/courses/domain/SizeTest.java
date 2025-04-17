package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SizeTest {

    @Test
    @DisplayName("커버 이미지 크기는 1MB 이하여야 한다.")
    void coverImageSizeOverTest() {
        Assertions.assertThat(new Size(1_048_576L)).isInstanceOf(Size.class);
        Assertions.assertThatThrownBy(() -> new Size(1_048_577L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커버 이미지의 크기는 1MB 이하여야 합니다.");;
    }

}