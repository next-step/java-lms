package nextstep.courses.domain.session;

import java.time.LocalDateTime;

import static nextstep.courses.ExceptionMessage.INVALID_PERIOD;

public class Period {
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public Period(LocalDateTime startedAt, LocalDateTime endedAt) {
        validatePeriodInput(startedAt, endedAt);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    private void validatePeriodInput(LocalDateTime startedAt, LocalDateTime endedAt) {
        if (startedAt.compareTo(endedAt) > 0) {
            throw new IllegalArgumentException(INVALID_PERIOD.message());
        }
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }
}
