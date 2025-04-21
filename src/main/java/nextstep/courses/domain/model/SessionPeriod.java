package nextstep.courses.domain.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class SessionPeriod {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

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

    @Override
    public String toString() {
        return "SessionPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
