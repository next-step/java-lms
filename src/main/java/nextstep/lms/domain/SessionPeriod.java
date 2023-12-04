package nextstep.lms.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
