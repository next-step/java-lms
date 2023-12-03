package nextstep.courses.domain;

import nextstep.courses.exception.NotCorrectPeriodException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PeriodTest {

    @DisplayName("시작 시간이 종료 시간이보다 크다면 예외가 발생한다.")
    @Test
    void throw_exception_when_start_time_is_later_than_end_time() {
        LocalDateTime start = LocalDateTime.of(2023, 12, 1, 12, 0);
        LocalDateTime end = LocalDateTime.of(2023, 11, 1, 12, 0);

        assertThatThrownBy(() -> new Period(start, end))
                .isInstanceOf(NotCorrectPeriodException.class);
    }

    @DisplayName("종료 시간이 종료 시간이보다 이르다면 예외가 발생한다.")
    @Test
    void throw_exception_when_end_time_is_earlier_than_start_time() {
        LocalDateTime start = LocalDateTime.of(2023, 12, 1, 12, 0);
        LocalDateTime end = LocalDateTime.of(2023, 1, 1, 12, 0);

        assertThatThrownBy(() -> new Period(start, end))
                .isInstanceOf(NotCorrectPeriodException.class);
    }
}