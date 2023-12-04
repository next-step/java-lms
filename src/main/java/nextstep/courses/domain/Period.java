package nextstep.courses.domain;

import java.time.LocalDate;

public class Period {

    private LocalDate startDate;
    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException();
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
