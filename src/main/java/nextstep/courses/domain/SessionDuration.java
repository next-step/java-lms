package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectDurationException;

import java.time.LocalDateTime;

public class SessionDuration {

    private final LocalDateTime startedAt;
    private final LocalDateTime endedAt;

    private SessionDuration(LocalDateTime startedAt, LocalDateTime endedAt) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public static SessionDuration create(LocalDateTime startedAt, LocalDateTime endedAt) {
        validate(startedAt, endedAt);
        return new SessionDuration(startedAt, endedAt);
    }

    private static void validate(LocalDateTime startedAt, LocalDateTime endedAt) {
        if (startedAt.isBefore(endedAt)) {
            return;
        }
        throw new IncorrectDurationException();
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }
}
