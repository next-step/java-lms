package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectDurationException;

import java.time.LocalDateTime;

public class SessionDuration {

    private final LocalDateTime startedAt;
    private final LocalDateTime endedAt;

    public SessionDuration(LocalDateTime startedAt, LocalDateTime endedAt) {
        validate(startedAt, endedAt);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
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
