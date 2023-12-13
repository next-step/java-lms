package nextstep.courses.domain;

import nextstep.courses.exception.InvalidPeriodException;
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
            throw new InvalidPeriodException();
        }

        if (endDate.isBefore(startDate)) {
            throw new InvalidPeriodRangeException();
        }
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
