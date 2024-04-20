package nextstep.courses.domain.Session;

import java.time.LocalDate;

public class Duration {

    Duration(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private final LocalDate startDate;
    private final LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
