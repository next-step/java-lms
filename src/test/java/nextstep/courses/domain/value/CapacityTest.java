package nextstep.courses.domain.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class CapacityTest {

    @Test
    void 최대_수강_인원이_음수라면_예외가_발생한다() {
        assertThatThrownBy(() -> new Capacity(-1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("최대 수강 인원은 0 이상이어야 합니다.");
    }

    @Test
    void 현재_수강_인원이_최대_수강_인원과_같거나_초과하면_true를_반환한다() {
        Capacity capacity = new Capacity(50);

        assertThat(capacity.isFull(50)).isTrue();
        assertThat(capacity.isFull(51)).isTrue();
    }

    @Test
    void 현재_수강_인원이_최대_수강_인원을_초과하지_않으면_false를_반환한다() {
        Capacity capacity = new Capacity(50);

        assertThat(capacity.isFull(49)).isFalse();
    }
}
