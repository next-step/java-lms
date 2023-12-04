package nextstep.courses.domain;

import nextstep.courses.exception.ProgressPeriodException;

import java.time.LocalDate;

public class ProgressPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    public ProgressPeriod(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private static void validate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new ProgressPeriodException("시작일은 종료일보다 미래일 수 없습니다");
        }
    }
}
