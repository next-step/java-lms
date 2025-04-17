package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WidthTest {

    @Test
    @DisplayName("너비 생성 테스트")
    void widthTest() {
        Assertions.assertThat(new Width(300.0)).isInstanceOf(Width.class);
        Assertions.assertThatThrownBy(() -> new Width(299.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("너비는 300px 이상이어야 합니다.");
    }

}