package nextstep.courses.domain;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class SessionPeriod {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public SessionPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public SessionPeriod(String startDate, String endDate) {
        this(LocalDate.parse(startDate, DATE_FORMATTER), LocalDate.parse(endDate, DATE_FORMATTER));
    }

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        this(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX.withNano(0)));
    }

    public LocalDateTime startDate() {
        return startDateTime;
    }

    public LocalDateTime endDate() {
        return endDateTime;
    }
}
