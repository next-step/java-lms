package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPeriod {
    public static final String INVALID_PERIOD_MSG = "종료일이 시작일보다 빠를 수 없습니다.";
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public SessionPeriod(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
        if (startDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException(INVALID_PERIOD_MSG);
        }

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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
