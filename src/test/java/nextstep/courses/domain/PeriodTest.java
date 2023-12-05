package nextstep.courses.domain;

import nextstep.courses.domain.session.Period;
import nextstep.courses.exception.NotCorrectTimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PeriodTest {

    @DisplayName("시작 시간이 종료 시간을 앞설 수 없다.")
    @Test
    void throw_exception_when_start_time_is_later_than_end_time() {
        LocalDateTime start = LocalDateTime.of(2023, 12, 1, 12, 0);
        LocalDateTime end = LocalDateTime.of(2023, 11, 1, 12, 0);

        assertThatThrownBy(() -> new Period(start, end))
                .isInstanceOf(NotCorrectTimeException.class);
    }
}
