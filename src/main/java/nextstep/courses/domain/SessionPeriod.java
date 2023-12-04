package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionPeriod {

    private final LocalDate startDate;

    private final LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
