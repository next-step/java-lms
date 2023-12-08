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

    public SessionStatus validate(SessionStatus status) throws PeriodException {
        if (SessionStatus.isClose(status) && endDate.isBefore(LocalDate.now())) {
            throw new PeriodException("종료는 강의 종료일 보다 뒤에 있어야합니다.");
        }

        if (!SessionStatus.isClose(status) && startDate.isAfter(LocalDate.now())) {
            throw new PeriodException("준비중과 모집일은 강의 시작일 전이여야한다.");
        }
        return status;
    }
}
