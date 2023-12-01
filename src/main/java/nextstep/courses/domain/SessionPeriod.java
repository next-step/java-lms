package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public SessionPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime startDate() {
        return startDateTime;
    }

    public LocalDateTime endDate() {
        return endDateTime;
    }
}
