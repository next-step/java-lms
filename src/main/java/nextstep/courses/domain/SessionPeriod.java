package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPeriod {
    private final LocalDateTime startedAt;
    private final LocalDateTime endedAt;

    public SessionPeriod(LocalDateTime startedAt, LocalDateTime endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("시작일은 종료일 이전이어야 합니다. startedAt: " + startedAt + " endedAt: " + endedAt
            );
        }

        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startedAt, that.startedAt) && Objects.equals(endedAt, that.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startedAt, endedAt);
    }
}
