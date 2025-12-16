package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CapacityTest {

    @Test
    @DisplayName("최대 인원을 넘어서 수강신청이 들어오면 Exceptionn")
    void max() {
        Capacity capacity = new Capacity(300, 301);
        assertThatThrownBy(() -> capacity.validateAvailable())
                .isInstanceOf(IllegalStateException.class);
    }
}