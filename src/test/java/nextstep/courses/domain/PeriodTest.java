package nextstep.courses.domain;

import nextstep.courses.exception.InvalidPeriodRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PeriodTest {

    @Test
    @DisplayName("종료일이 시작일보다 과거일 경우 예외를 던진다.")
    void validate_period() {
        assertThatThrownBy(() -> new Period(LocalDate.of(2023, 12, 14), LocalDate.of(2023, 12, 10)))
                .isInstanceOf(InvalidPeriodRangeException.class);
    }
}
