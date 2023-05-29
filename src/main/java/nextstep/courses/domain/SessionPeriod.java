package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {

    private LocalDate startDate;

    private LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isEndDateBeforeNow() {
        return endDate.isBefore(LocalDate.now());
    }
}
