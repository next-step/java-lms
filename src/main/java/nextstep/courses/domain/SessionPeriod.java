package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class SessionPeriod {

    private LocalDate startDate;
    private LocalDate endDate;

    public SessionPeriod(LocalDate startDate){
        this.startDate = startDate;
        this.endDate = startDate.plusDays(30);
    }

    public SessionPeriod(LocalDate startDate, LocalDate endDate){
        validateDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDate(LocalDate startedAt, LocalDate endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("강의 시작일은 강의 종료일 보다 빨라야 합니다.");
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
        SessionPeriod that = (SessionPeriod) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
