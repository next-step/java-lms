package nextstep.courses.domain.vo.session;

import nextstep.courses.EndBeforeStartException;

import java.time.LocalDateTime;
import java.util.Objects;

public class DateRange {
    public static final String END_BEFORE_START_MESSAGE = "종료일이 시작일보다 과거일수 없습니다.";
    private final LocalDateTime start;
    private final LocalDateTime end;

    public DateRange(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new EndBeforeStartException(END_BEFORE_START_MESSAGE);
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRange dateRange = (DateRange) o;
        return Objects.equals(start, dateRange.start) && Objects.equals(end, dateRange.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
