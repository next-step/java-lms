package nextstep.sessions.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CapacityTest {

    public static final Capacity FREE_CAPACITY = new Capacity(Integer.MAX_VALUE, true);
    public static final Capacity PAID_CAPACITY = new Capacity(10, false);

    @Test
    void freeCapacity_isUnlimitedIsTrue() {
        assertThat(FREE_CAPACITY.isUnlimited()).isTrue();
    }

    @Test
    void paidCapacity_maxCapacityMustBePositive() {
        assertThat(PAID_CAPACITY.maxCapacity()).isEqualTo(10);
    }

    @Test
    void canEnroll_enrollmentsNotFullReturnsTrue() {
        assertThat(FREE_CAPACITY.canEnroll(100)).isTrue();
        assertThat(PAID_CAPACITY.canEnroll(0)).isTrue();
    }

    @Test
    void canEnroll_enrollmentsFullReturnsFalse() {
        assertThat(PAID_CAPACITY.canEnroll(10)).isFalse();
    }

    @Test
    void paidCapacity_invalidMaxCapacity_throwsException() {
        assertThatThrownBy(() -> new Capacity(0, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유료 강의는 최대 수강인원이 있어야 합니다");
    }

    @Test
    void enrollEqualsMax_isFullReturnsTrue() {
        Capacity capacity = new Capacity(3, false);
        assertThat(capacity.isFull(3)).isTrue();
    }

    @Test
    void enrollLessThanMax_isFullReturnsFalse() {
        Capacity capacity = new Capacity(3, false);
        assertThat(capacity.isFull(2)).isFalse();
    }


}
