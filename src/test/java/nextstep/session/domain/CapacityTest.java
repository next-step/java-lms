package nextstep.session.domain;

import nextstep.exception.CapacityException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CapacityTest {

    @DisplayName("최대 수용 인원은 음수일 수 없다. 음수라면 CapacityException을 던진다.")
    @Test
    void throwCapacityExceptionWhenMaxCapacityIsMinus() {
        // then
        assertThatThrownBy(() -> new Capacity(-10))
                .isInstanceOf(CapacityException.class);
    }

    @DisplayName("수강신청 인원만큼 인원을 추가한다.")
    @Test
    void addEnrolled() {
        // given
        Capacity capacity = new Capacity(10);

        // then
        capacity.enroll(5);
        assertThat(capacity.getEnrolled()).isEqualTo(5);
        capacity.enroll(3);
        assertThat(capacity.getEnrolled()).isEqualTo(8);
    }

    @DisplayName("수용가능 인원을 초과해서 추가한다면, CapacityException을 던진다.")
    @Test
    void throwCapacityExceptionWhenOverMaxCapacity() {
        // given
        Capacity capacity = new Capacity(10);

        // then
        assertThatThrownBy(() -> capacity.enroll(11))
                .isInstanceOf(CapacityException.class);
    }

    @DisplayName("수강신청 인원이 최대 수용인원보다 적다면, 사용 가능하다.")
    @Test
    void availableWhenMasCapacityLessThanEnrolled() {
        // given
        Capacity capacity = new Capacity(10);

        // when
        capacity.enroll(5);

        // then
        assertThat(capacity.isAvailable()).isTrue();
    }

    @DisplayName("수강신청 인원이 최대 수용인원보다 같거나 많다면, 사용 불가능하다.")
    @Test
    void notAvailableWhenMasCapacityBiggerThanEnrolled() {
        // given
        Capacity capacity = new Capacity(10);

        // when
        capacity.enroll(10);

        // then
        assertThat(capacity.isAvailable()).isFalse();
    }
}
