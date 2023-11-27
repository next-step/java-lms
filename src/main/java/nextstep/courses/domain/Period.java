package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectDateException;

import java.time.LocalDate;

public class Period {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate,
                  LocalDate endDate) {
        if (isNull(startDate, endDate)) {
            throw new IncorrectDateException("날짜는 필수값 입니다.");
        }


        if (startDate.isAfter(endDate)) {
            throw new IncorrectDateException("시작일은 종료일 보다 늦을 수 없습니다.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private boolean isNull(LocalDate startDate,
                           LocalDate endDate) {
        return startDate == null || endDate == null;
    }
}
