package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionDuration {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public SessionDuration(LocalDateTime startDate, LocalDateTime endDate) {
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (!startDate.isBefore(endDate)) {
            throw new IllegalArgumentException();
        }
    }
}
