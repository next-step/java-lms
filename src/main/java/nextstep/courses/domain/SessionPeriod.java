package nextstep.courses.domain;

import nextstep.courses.exception.SessionPeriodException;

import java.time.LocalDate;

public class SessionPeriod {

    private final LocalDate startDate;

    private final LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        validateDateRange(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private static void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new SessionPeriodException("시작일은 종료일 보다 작아야 합니다.");
        }
    }
}
