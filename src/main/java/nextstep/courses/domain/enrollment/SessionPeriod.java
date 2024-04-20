package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionPeriodRangeException;

import java.time.LocalDateTime;

public class SessionPeriod {

    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public SessionPeriod(LocalDateTime startAt, LocalDateTime endAt) {
        validate(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validate(LocalDateTime startAt, LocalDateTime endAt) {
        if (invalidRange(startAt, endAt)) {
            throw new SessionPeriodRangeException(startAt, endAt);
        }
    }

    private boolean invalidRange(LocalDateTime startAt, LocalDateTime endAt) {
        return endAt.isBefore(startAt);
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
