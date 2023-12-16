package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionDateTime {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SessionDateTime(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
