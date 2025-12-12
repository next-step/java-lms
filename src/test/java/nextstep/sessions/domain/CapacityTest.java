package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CapacityTest {

    @Test
    void freeCapacity_maxCapacityIsNull() {
        Capacity freeCapacity = new Capacity(null);
        assertThat(freeCapacity.maxCapacity()).isNull();
        assertThat(freeCapacity.canEnroll()).isTrue();
    }

    @Test
    void paidCapacity_maxCapacityMustBePositive() {
        Capacity paidCapacity = new Capacity(5, 0);
        assertThat(paidCapacity.maxCapacity()).isEqualTo(5);
        assertThat(paidCapacity.canEnroll()).isTrue();
    }

    @Test
    void paidCapacity_invalidMaxCapacity_throwsException() {
        assertThatThrownBy(() -> new Capacity(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 최대 수강인원이 있어야 합니다");
    }

    @Test
    void enrollCountExceedsMax_throwsException() {
        assertThatThrownBy(() -> new Capacity(1, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수강 인원은 수강 정원을 초과할 수 없습니다");
    }

    @Test
    void increaseEnrollCount_incrementsEnrollCountByOne() {
        Capacity capacity = new Capacity(null);
        Capacity afterIncrease = capacity.increaseEnrollCount();
        assertThat(afterIncrease.enrollCount() - capacity.enrollCount()).isEqualTo(1);
    }

}
