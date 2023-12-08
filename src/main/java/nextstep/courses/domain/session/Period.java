package nextstep.courses.domain.session;

import nextstep.courses.exception.NotCorrectTimeException;

import java.time.LocalDate;
import java.util.Objects;

public class Period {

    private final LocalDate startAt;

    private final LocalDate endAt;

    public Period(LocalDate startAt, LocalDate endAt) {
        validateStartAt(startAt, endAt);
        this.startAt = startAt;
        this.endAt = endAt;
    }

    private void validateStartAt(LocalDate startAt, LocalDate endAt) {
        if (startAt.isAfter(endAt)) {
            throw new NotCorrectTimeException(startAt, endAt);
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
