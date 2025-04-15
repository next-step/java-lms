package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceTest {
    @Test
    void 가격이_음수이면_예외발생() {
        assertThatThrownBy(() -> Price.of(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가격은 0 이상이어야 합니다.");
    }
}
