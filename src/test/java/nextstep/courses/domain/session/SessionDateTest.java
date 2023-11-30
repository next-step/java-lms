package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDateTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("시작일에 Null이 들어가면, 예외가 발생한다.")
    void testStartDateIsNotNull(LocalDateTime startDate) {
        //given
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        //when, then
        assertThatThrownBy(() -> new SessionDate(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("date cannot be null");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("종료일에 Null이 들어가면, 예외가 발생한다.")
    void testEndDateIsNotNull(LocalDateTime endDate) {
        //given
        final LocalDateTime startDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        //when, then
        assertThatThrownBy(() -> new SessionDate(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("date cannot be null");
    }

    @Test
    @DisplayName("시작일이 종료일보다 느리면, 예외가 발생한다.")
    void testStartDateIsBeforeEndDate() {
        //given
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 2, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 1, 1, 23, 59);

        //when, then
        assertThatThrownBy(() -> new SessionDate(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start date cannot be after end date");
    }
}
