package nextstep.courses.domain.session;

import nextstep.courses.EndBeforeStartException;

import java.time.LocalDateTime;
import java.util.Objects;

public class DateRange {
    public static final String END_BEFORE_START_MESSAGE = "종료일이 시작일보다 과거일수 없습니다.";
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public DateRange(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new EndBeforeStartException(END_BEFORE_START_MESSAGE);
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(startAt, dateRange.startAt) && Objects.equals(endAt, dateRange.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startAt, endAt);
    }
}
