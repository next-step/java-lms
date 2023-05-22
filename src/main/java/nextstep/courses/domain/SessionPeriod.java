package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 종료일은 시작일 이후이어야 합니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
