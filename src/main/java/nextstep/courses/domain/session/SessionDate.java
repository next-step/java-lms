package nextstep.courses.domain.session;

import java.time.LocalDate;

public class SessionDate {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isBefore(LocalDate date) {
        return date.isBefore(startDate);
    }
}
