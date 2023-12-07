package nextstep.sessions.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    LocalDateTime startDay;
    LocalDateTime endDay;

    public SessionPeriod(LocalDateTime startDay, LocalDateTime endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }
}
