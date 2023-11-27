package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PeriodTest {

    @Test
    @DisplayName("시작일이 종료일 보다 이후 일 경우 예외 처리 된다")
    void newPeriod() {
        LocalDate startDate = LocalDate.of(2023, 11, 23);
        LocalDate endDate = LocalDate.of(2023, 11, 22);
        assertThrows(IncorrectDateException.class, () -> new Period(startDate, endDate),
                "시작일은 종료일 보다 늦을 수 없습니다.");
    }

    @Test
    @DisplayName("시작일 혹은 종료일은 null이 올 수 없다")
    void newPeriod2() {
        Assertions.assertAll(
                () -> {
                    LocalDate startDate = null;
                    LocalDate endDate = LocalDate.of(2023, 11, 22);

                    assertThrows(IncorrectDateException.class, () -> new Period(startDate, endDate),
                            "날짜는 필수값 입니다.");
                },
                () -> {
                    LocalDate startDate = LocalDate.of(2023, 11, 22);
                    LocalDate endDate = null;

                    assertThrows(IncorrectDateException.class, () -> new Period(startDate, endDate),
                            "날짜는 필수값 입니다.");
                },
                () -> {
                    LocalDate startDate = null;
                    LocalDate endDate = null;

                    assertThrows(IncorrectDateException.class, () -> new Period(startDate, endDate),
                            "날짜는 필수값 입니다.");
                }
        );
    }
}
