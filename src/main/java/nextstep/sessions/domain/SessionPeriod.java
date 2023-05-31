package nextstep.sessions.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = null;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

}
