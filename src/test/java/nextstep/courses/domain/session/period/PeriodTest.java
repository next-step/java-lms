package nextstep.courses.domain.session.period;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class PeriodTest {

    @DisplayName("시작일이 종료일 보다 앞서면 예외를 던진다.")
    @Test
    void validatePeriod() {
        // given
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 11, 30);

        // when & then
        assertThatThrownBy(() -> new Period(startDate, endDate)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("강의 시작일은 강의 종료일보다 앞서야 합니다. 시작일 : 2023-12-01, 종료일 : 2023-11-30");
    }
}