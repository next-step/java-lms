package nextstep.courses.domain.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public SessionPeriod(Date startDate, Date endDate) {
        this(startDate.toLocalDate().atStartOfDay(), endDate.toLocalDate().atStartOfDay());
    }


    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        validateSessionDates(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateSessionDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("start date must be before end date");
        }
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

}
