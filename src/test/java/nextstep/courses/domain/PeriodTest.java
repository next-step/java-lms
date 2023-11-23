package nextstep.courses.domain;

import nextstep.courses.CannotStartDateAfterEndDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PeriodTest {

    @Test
    @DisplayName("시작일이 종료일 보다 이후 일 경우 예외 처리 된다")
    void newPeriod() {
        LocalDate startDate = LocalDate.of(2023, 11, 23);
        LocalDate endDate = LocalDate.of(2023, 11, 22);
        Assertions.assertThrows(CannotStartDateAfterEndDateException.class, () -> new Period(startDate, endDate),
                "시작일은 종료일 보다 늦을 수 없습니다.");
    }
}
