package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPeriod {
    public static final String INVALID_PERIOD_MESSAGE = "강의 기간을 확인해주세요.";

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public SessionPeriod(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
        validateSessionPeriod(startDateTime, endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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
        return Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime);
    }
}
