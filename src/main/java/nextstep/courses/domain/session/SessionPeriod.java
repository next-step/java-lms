package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPeriod {
    public static final String INVALID_PERIOD_MESSAGE = "강의 기간을 확인해주세요.";

    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public SessionPeriod(final LocalDateTime startAt, final LocalDateTime endAt) {
        validateSessionPeriod(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validateSessionPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException(INVALID_PERIOD_MESSAGE);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startAt, that.startAt) && Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startAt, endAt);
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
