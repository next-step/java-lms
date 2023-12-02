package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionPeriod {

    private LocalDate startDate;

    private LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
