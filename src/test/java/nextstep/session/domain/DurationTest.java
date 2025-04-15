package nextstep.session.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DurationTest {

    @Test
    @DisplayName("유효한 시작일과 종료일로 Duration을 생성할 수 있다")
    void createValidDuration() {
        LocalDate start = LocalDate.of(2025, 5, 1);
        LocalDate end = LocalDate.of(2025, 5, 30);

        Duration duration = new Duration(start, end);

        assertThat(duration.getStartAt()).isEqualTo(start);
        assertThat(duration.getEndAt()).isEqualTo(end);
    }

    @Test
    @DisplayName("시작일 또는 종료일이 null이면 예외를 던진다")
    void nullStartOrEndDate() {
        LocalDate validDate = LocalDate.of(2025, 5, 1);

        assertThatThrownBy(() -> new Duration(null, validDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일과 종료일은 필수");

        assertThatThrownBy(() -> new Duration(validDate, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일과 종료일은 필수");
    }

    @Test
    @DisplayName("종료일이 시작일보다 빠르면 예외를 던진다")
    void endDateBeforeStartDate() {
        LocalDate start = LocalDate.of(2025, 5, 30);
        LocalDate end = LocalDate.of(2025, 5, 1);

        assertThatThrownBy(() -> new Duration(start, end))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("종료일은 시작일보다 이후여야");
    }

    @Test
    @DisplayName("동일한 값의 Duration은 equals/hashCode가 같다")
    void equalityTest() {
        LocalDate start = LocalDate.of(2025, 5, 1);
        LocalDate end = LocalDate.of(2025, 5, 30);

        Duration duration1 = new Duration(start, end);
        Duration duration2 = new Duration(start, end);

        assertThat(duration1).isEqualTo(duration2);
        assertThat(duration1.hashCode()).isEqualTo(duration2.hashCode());
    }

    @Test
    @DisplayName("다른 값의 Duration은 equals가 다르다")
    void notEqualDuration() {
        Duration duration1 = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 30));
        Duration duration2 = new Duration(LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 30));

        assertThat(duration1).isNotEqualTo(duration2);
    }

    @Test
    @DisplayName("기간 내에 포함되는 날짜는 true를 반환한다.")
    void durationContainsDayReturnTrue() {
        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 30));

        assertThat(duration.contains(LocalDate.of(2025, 4, 2))).isTrue();
    }

    @Test
    @DisplayName("기간 내에 포함되지 않는 날짜는 false를 반환한다.")
    void durationContainsDayReturnFalse() {
        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 30));

        assertThat(duration.contains(LocalDate.of(2025, 5, 1))).isFalse();
    }

    @Test
    @DisplayName("기간내의 날짜를 계속해서 반환한다.")
    void durationCanReturnDays() {
        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5));

        assertThat(duration.days()).isEqualTo(5);
    }

    @Test
    @DisplayName("강의 마지막날 전에는 환불을 신청할 수 있다.")
    void canBeRefundBeforeEnd() {
        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5));

        assertThat(duration.canBeRefund(LocalDate.of(2025, 4, 4))).isTrue();
    }

    @Test
    @DisplayName("강의가 끝난후에는 환불을 신청할 수 없다.")
    void canNotBeRefundBeforeEnd() {
        Duration duration = new Duration(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 5));

        assertThat(duration.canBeRefund(LocalDate.of(2025, 4, 6))).isFalse();
    }
}
