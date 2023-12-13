package nextstep.courses.domain;

import java.time.LocalDate;

public enum SessionStatus {
    PREPARING,
    IN_PROGRESS,
    COMPLETED;

    public static SessionStatus of(LocalDate now, LocalDate startDate, LocalDate endDate) {
        if (now.isBefore(startDate)) {
            return PREPARING;
        }

        if (now.isAfter(startDate) && now.isBefore(endDate)) {
            return IN_PROGRESS;
        }

        return COMPLETED;
    }

    public boolean isInProgress() {
        return this == IN_PROGRESS;
    }
}
