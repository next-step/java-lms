package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CapacityTest {

    @Test
    @DisplayName("현재인원이 최대인원이면 수강신청 불가능")
    void max() {
        Capacity capacity = new Capacity(300, 300);
        assertThatThrownBy(() -> capacity.validateAvailable())
                .isInstanceOf(IllegalStateException.class);
    }
}