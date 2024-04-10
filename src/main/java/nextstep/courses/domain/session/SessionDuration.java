package nextstep.courses.domain.session;

import nextstep.courses.exception.InvalidSessionDurationException;

import java.time.LocalDateTime;

public class SessionDuration {

    private final Long id;
    private final Long sessionId;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public SessionDuration(Long id, Long sessionId, LocalDateTime startAt, LocalDateTime endAt) {
        validateDuration(startAt, endAt);
        this.id = id;
        this.sessionId = sessionId;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validateDuration(LocalDateTime startAt, LocalDateTime endAt) {
        if (!isValidAtRange(startAt, endAt)) {
            throw new InvalidSessionDurationException(startAt, endAt);
        }
    }

    private boolean isValidAtRange(LocalDateTime startAt, LocalDateTime endAt) {
        return startAt.isBefore(endAt);
    }

}
