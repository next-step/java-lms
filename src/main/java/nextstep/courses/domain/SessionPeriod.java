package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {
    private final LocalDate startedAt;
    private final LocalDate endedAt;

    public SessionPeriod(LocalDate startedAt, LocalDate endedAt) {
        validateDate(startedAt, endedAt);
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public LocalDate getEndedAt() {
        return endedAt;
    }

    private void validateDate(LocalDate startedAt, LocalDate endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("강의 시작인은 종료일 이후여야 합니다");
        }
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
