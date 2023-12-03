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
            throw new ProgressPeriodException("종료일은 시작일보다 미래일 수 없습니다");
        }
    }
}
