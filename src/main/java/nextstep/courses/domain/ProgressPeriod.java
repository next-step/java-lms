package nextstep.courses.domain;

import nextstep.courses.exception.ProgressPeriodException;

import java.time.LocalDate;
import java.util.Objects;

public class ProgressPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    public ProgressPeriod(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private static void validate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new ProgressPeriodException("시작일은 종료일보다 미래일 수 없습니다");
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressPeriod that = (ProgressPeriod) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public String toString() {
        return "ProgressPeriod{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
