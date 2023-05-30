package nextstep.courses.domain;

import java.time.LocalDateTime;

public class DateRange {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public DateRange(LocalDateTime startDate) {
        this.startDate = startDate;
        this.endDate = null;
    }
}
