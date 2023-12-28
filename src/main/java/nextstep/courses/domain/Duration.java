package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Duration {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;


    public Duration(LocalDateTime startDate, LocalDateTime endDate) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Duration(Duration duration) {
        this(duration.startDate, duration.endDate);
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new IllegalArgumentException(ExceptionMessage.DURATION_RANGE.getMessage());
        }
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
