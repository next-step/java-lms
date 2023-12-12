package nextstep.courses.domain.session;

import java.time.LocalDate;

public enum SessionStatus {
    PREPARING, ENROLLING, CLOSED;

    public static final SessionStatus status(Session session) {
        if (LocalDate.now().isBefore(session.startDate)) {
            return PREPARING;
        }
        if (LocalDate.now().isAfter(session.endDate)) {
            return CLOSED;
        }
        return ENROLLING;
    }

    public static final boolean isEnrollmentPeriod(Session session) {
        return status(session) == ENROLLING;
    }
}
