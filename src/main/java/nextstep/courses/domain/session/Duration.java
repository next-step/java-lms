package nextstep.courses.domain.session;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.courses.InvalidTimeRangeException;

public class Duration {

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public Duration(LocalDateTime startDate, LocalDateTime endDate) throws InvalidTimeRangeException {
        this.startDate = startDate;
        this.endDate = valid(endDate);
    }

    private LocalDateTime valid(LocalDateTime endDate) throws InvalidTimeRangeException {
        if (endDate.isBefore(startDate)) {
            throw new InvalidTimeRangeException("시작기한은 종료기한 이후일 수 없습니다.");
        }
        return endDate;
    }

    public static Duration of(LocalDateTime startDate, LocalDateTime endDate)
        throws InvalidTimeRangeException {
        return new Duration(startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Duration that = (Duration) o;
        return Objects.equals(startDate,
            that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
