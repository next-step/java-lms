package nextstep.sessions.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    LocalDateTime startDate;
    LocalDateTime endDate;

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
