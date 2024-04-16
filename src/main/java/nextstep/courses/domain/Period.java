package nextstep.courses.domain;

import nextstep.courses.PeriodException;

import java.time.LocalDate;

public class Period {
    private final LocalDate startAt;
    private final LocalDate endAt;

    public Period(LocalDate startAt, LocalDate endAt) throws PeriodException {
        checkPeriod(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void checkPeriod(LocalDate startAt, LocalDate endAt) throws PeriodException {
        if (startAt.isAfter(endAt)) {
            throw new PeriodException("강의 종료일이 시작일보다 빠릅니다.");
        }
    }
}
