package nextstep.session.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DurationTest {

    @DisplayName("시작일자가 종료일자보다 미래면 IllegalArgumentException을 던진다.")
    @Test
    void throwIllegalArgumentExceptionWhenStartDateIsLaterThanEndDate() {
        // given
        LocalDateTime startDate = LocalDateTime.now().plusDays(5);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        // then
        assertThatThrownBy(() -> new Duration(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("조회하는 일자가 시작일자와 종료일자 사이인지 조회한다.")
    @Test
    void isAvailable() {
        // given
        LocalDateTime startDate = LocalDateTime.now().plusDays(10);
        LocalDateTime endDate = LocalDateTime.now().plusDays(20);
        LocalDateTime queryDateFit = LocalDateTime.now().plusDays(11);
        LocalDateTime queryDateLater = LocalDateTime.now().plusDays(22);

        // when
        Duration duration = new Duration(startDate, endDate);

        // then
        assertThat(duration.isAvailable(queryDateFit)).isTrue();
        assertThat(duration.isAvailable(queryDateLater)).isFalse();
    }

    @DisplayName("시작일자가 현재보다 과거라면, IllegalArgumentException를 던진다.")
    @Test
    void throwIllegalArgumentExceptionWhenStartDateIsPast() {
        // given
        LocalDateTime startDate = LocalDateTime.now().minusDays(5);;
        LocalDateTime endDate = LocalDateTime.now().plusDays(5);

        // then
        assertThatThrownBy(() -> new Duration(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("시작일자를 변경하면, 새로운 객체를 반환한다.")
    @Test
    void changeStartDate() {
        // given
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(5);
        Duration duration = new Duration(startDate, endDate);

        // when
        duration = duration.changeStartDate(LocalDateTime.now().plusDays(4));

        // then
        assertThat(duration.isAvailable(LocalDateTime.now().plusDays(3))).isFalse();

    }

    @DisplayName("종료일자를 변경하면, 새로운 객체를 반환한다.")
    @Test
    void changeEndDate() {
        // given
        LocalDateTime startDate = LocalDateTime.now().plusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(5);
        Duration duration = new Duration(startDate, endDate);

        // when
        duration = duration.changeEndDate(LocalDateTime.now().plusDays(2));

        // then
        assertThat(duration.isAvailable(LocalDateTime.now().plusDays(3))).isFalse();
    }
}
