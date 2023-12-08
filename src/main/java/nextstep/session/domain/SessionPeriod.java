package nextstep.session.domain;

import java.time.LocalDate;

public class SessionPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isStart(PeriodStrategy periodStrategy) {
        LocalDate currentDate = periodStrategy.getLocalDate();
        return currentDate.isEqual(startDate) || currentDate.isAfter(startDate);
    }
}
