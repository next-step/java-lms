package nextstep.courses.domain.session;

import java.time.LocalDate;

public class SessionDuration {

    SessionDuration(LocalDate startDate, LocalDate endDate) {
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
