package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionPeriodRangeException;

import java.time.LocalDateTime;

public class SessionPeriod {

    private final Long id;
    private final Long sessionId;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public SessionPeriod(Long id, Long sessionId, LocalDateTime startAt, LocalDateTime endAt) {
        validateDuration(startAt, endAt);
        this.id = id;
        this.sessionId = sessionId;
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

}