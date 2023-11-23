package nextstep.courses.domain;

import nextstep.courses.CannotStartDateAfterEndDateException;

import java.time.LocalDate;

public class Period {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate,
                  LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new CannotStartDateAfterEndDateException("시작일은 종료일 보다 늦을 수 없습니다.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }
}
