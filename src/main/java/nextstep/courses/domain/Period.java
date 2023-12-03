package nextstep.courses.domain;

import nextstep.courses.exception.NotCorrectPeriodException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Period {

    private final LocalDateTime startAt;

    private final LocalDateTime endAt;

    public Period(LocalDateTime startAt, LocalDateTime endAt) {
        validateStartAt(startAt, endAt);
        validateEndAt(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validateEndAt(LocalDateTime startAt, LocalDateTime endAt) {
        if (endAt.isBefore(startAt)) {
            throw new NotCorrectPeriodException();
        }
    }

    private void validateStartAt(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new NotCorrectPeriodException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startAt, period.startAt) && Objects.equals(endAt, period.endAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startAt, endAt);
    }
}
