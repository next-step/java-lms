package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionPeriodRangeException;

import java.time.LocalDateTime;

public class SessionPeriod {

    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public SessionPeriod(LocalDateTime startAt, LocalDateTime endAt) {
        validateDuration(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validateDuration(LocalDateTime startAt, LocalDateTime endAt) {
        if (!isValidAtRange(startAt, endAt)) {
            throw new SessionPeriodRangeException(startAt, endAt);
        }
    }

    private boolean isValidAtRange(LocalDateTime startAt, LocalDateTime endAt) {
        return startAt.isBefore(endAt);
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
