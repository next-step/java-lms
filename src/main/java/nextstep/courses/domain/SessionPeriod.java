package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPeriod {
    private static final String INVALID_PERIOD_MESSAGE = "강의 시작일은 종료일 이전이어야 합니다.";

    private final LocalDateTime startedAt;
    private final LocalDateTime endAt;

    public SessionPeriod(LocalDateTime startedAt, LocalDateTime endAt) {
        validatePeriod(startedAt, endAt);
        this.startedAt = startedAt;
        this.endAt = endAt;
    }

    private void validatePeriod(LocalDateTime startedAt, LocalDateTime endAt) {
        if (endAt.isBefore(startedAt)) {
            throw new IllegalArgumentException(INVALID_PERIOD_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionPeriod that = (SessionPeriod) o;

        if (!Objects.equals(startedAt, that.startedAt)) return false;
        return Objects.equals(endAt, that.endAt);
    }

    @Override
    public int hashCode() {
        int result = startedAt != null ? startedAt.hashCode() : 0;
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        return result;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
