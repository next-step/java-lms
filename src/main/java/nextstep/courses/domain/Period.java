package nextstep.courses.domain;

import java.time.LocalDate;

public class Period {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작일은 종료일보다 앞이어야 합니다.");
        }
    }
}
