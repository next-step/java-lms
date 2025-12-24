package nextstep.courses.cohort.domain;

import static java.util.Objects.isNull;

import java.time.LocalDateTime;

public class Period {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        if (isNull(startDate) || isNull(endDate)) {
            throw new IllegalArgumentException("수강신청 시작일과 종료일은 필수값 입니다.");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("수강신청 시작일이 종료일보다 미래일수는 없습니다");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isPeriodIn(LocalDateTime targetDate) {
        boolean startDateAfter = this.startDate.isBefore(targetDate);
        boolean endDateBefore = this.endDate.isAfter(targetDate);

        return startDateAfter && endDateBefore;
    }

    public boolean isOverEndDate(LocalDateTime targetDate) {
        return this.endDate.isBefore(targetDate);
    }

    public boolean isBeforeStartDate(LocalDateTime targetDate) {
        return this.startDate.isAfter(targetDate);
    }
}
