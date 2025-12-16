package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CapacityTest {

    public static final Capacity FREE_CAPACITY = new Capacity(Integer.MAX_VALUE, true);
    public static final Capacity PAID_CAPACITY = new Capacity(10, true);

    @Test
    void freeCapacity_isUnlimitedIsTrue() {
        assertThat(FREE_CAPACITY.isUnlimited()).isTrue();
        assertThat(FREE_CAPACITY.canEnroll()).isTrue();
    }

    @Test
    void paidCapacity_maxCapacityMustBePositive() {
        assertThat(PAID_CAPACITY.maxCapacity()).isEqualTo(10);
        assertThat(PAID_CAPACITY.canEnroll()).isTrue();
    }

    @Test
    void paidCapacity_invalidMaxCapacity_throwsException() {
        assertThatThrownBy(() -> new Capacity(0, false, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 최대 수강인원이 있어야 합니다");
    }

    @Test
    void enrollCountExceedsMax_throwsException() {
        assertThatThrownBy(() -> new Capacity(1, false, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수강 인원은 수강 정원을 초과할 수 없습니다");
    }

    @Test
    void enrollEqualsMax_isFullReturnsTrue() {
        Capacity capacity = new Capacity(3, false, 3);
        assertThat(capacity.isFull()).isTrue();
    }

    @Test
    void enrollLessThanMax_isFullReturnsFalse() {
        Capacity capacity = new Capacity(3, false, 2);
        assertThat(capacity.isFull()).isFalse();
    }

    @Test
    void increaseEnrollCount_incrementsEnrollCountByOne() {
        Capacity afterIncrease = FREE_CAPACITY.increaseEnrollCount();
        assertThat(afterIncrease.enrollCount() - FREE_CAPACITY.enrollCount()).isEqualTo(1);
    }

}
