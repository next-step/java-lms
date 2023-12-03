package nextstep.courses.domain;

import nextstep.courses.exception.InvalidPeriodRangeException;

import java.time.LocalDate;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        validatePeriod(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validatePeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("시작일, 종료일은 필수값입니다.");
        }

        if (endDate.isBefore(startDate)) {
            throw new InvalidPeriodRangeException();
        }
    }

    public boolean isDateWithinRange(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
