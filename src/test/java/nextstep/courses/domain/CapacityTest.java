package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.Capacity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CapacityTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    public void capacity는_1_이상이어야_한다(int value) throws Exception {
        assertThatThrownBy(() -> {
            new Capacity(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void capacity_를_변경할_수_있다() throws Exception {
        Capacity capacity = new Capacity(1);

        capacity.update(2);

        assertThat(capacity.value()).isEqualTo(2);
    }

    @Test
    public void capacity_는_동등_비교가_가능하다() throws Exception {
        Capacity smaller = new Capacity(1);
        Capacity larger = new Capacity(2);

        assertThat(smaller).isEqualTo(smaller);
        assertThat(smaller).isLessThan(larger);
        assertThat(larger).isGreaterThan(smaller);
    }
}