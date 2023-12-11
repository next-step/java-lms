package nextstep.courses.domain;

import java.time.LocalDate;

public class Period {

    private final LocalDate startDate;

    private final LocalDate endDate;


    public Period(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 먼저일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }
}
