package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Session() {
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
