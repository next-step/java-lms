package nextstep.courses.domain.session;

import java.time.LocalDate;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        validateDateOrder(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDateOrder(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException();
        }
    }
}
