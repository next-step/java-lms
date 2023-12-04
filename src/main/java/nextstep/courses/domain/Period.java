package nextstep.courses.domain;

import java.time.LocalDate;

public class Period {

    private LocalDate startDate;
    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) throws PeriodException {
        if(startDate.isAfter(endDate)) {
            throw new PeriodException("시작일자가 종료일보다 미래일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
