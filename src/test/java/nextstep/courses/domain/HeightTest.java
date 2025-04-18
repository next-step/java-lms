package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeightTest {

    @Test
    @DisplayName("높이 생성 테스트")
    void heightCreateTest() {
        Assertions.assertThat(new Height(200))
                .isInstanceOf(Height.class);

        Assertions.assertThatThrownBy(() -> new Height(199))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("높이는 200px 이상이어야 합니다.");
    }

}
