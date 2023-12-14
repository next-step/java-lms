package nextstep.courses.domain.session;

import java.time.LocalDate;

public enum SessionStatus {
    PREPARING, ENROLLING, CLOSED;

    public static final SessionStatus status(Schedule schedule) {
        if (schedule.isBeforeStartDate(LocalDate.now())) {
            return PREPARING;
        }
        if (schedule.isAfterEndDate(LocalDate.now())) {
            return CLOSED;
        }
        return ENROLLING;
    }
}
