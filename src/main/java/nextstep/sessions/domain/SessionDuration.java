package nextstep.sessions.domain;

import nextstep.sessions.IncorrectDurationException;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionDuration that = (SessionDuration) o;
        return Objects.equals(startedAt, that.startedAt) && Objects.equals(endedAt, that.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startedAt, endedAt);
    }

    @Override
    public String toString() {
        return "SessionDuration{" +
                "startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                '}';
    }

}
