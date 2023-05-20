package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionDate {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public SessionDate(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
