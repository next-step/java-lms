package nextstep.courses.domain;

import nextstep.courses.domain.session.Period;
import nextstep.courses.exception.NotCorrectTimeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class PeriodTest {

    @DisplayName("시작 시간이 종료 시간을 앞설 수 없다.")
    @Test
    void throw_exception_when_start_time_is_later_than_end_time() {
        LocalDate start = LocalDate.now().plusDays(1);
        LocalDate end = LocalDate.now();

        assertThatThrownBy(() -> new Period(start, end))
                .isInstanceOf(NotCorrectTimeException.class);
    }
}
